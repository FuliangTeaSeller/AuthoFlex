# AuthoFlex - 轻量级认证授权框架

## 项目简介

一款轻量级、可扩展的Java认证授权框架，支持内存/缓存/Redis等多存储后端，开箱即用。旨在简化Token管理、权限校验等重复开发工作，提供统一配置接口，帮助开发者快速集成认证授权功能到项目中。

## 核心特性

- **多存储支持**：默认使用内存`ConcurrentHashMap`存储，支持通过引入`autho-flex-cache`模块使用Hutool Cache，或通过`autho-flex-redis`模块集成Redis。
- **注解鉴权**：提供可选的拦截器模式，支持快速集成到Spring等框架中，对标注认证注解的接口自动校验。
- **兼容设计**：core模块无强制第三方依赖，各模块按需引入。

## 快速开始

### 前置条件

- JDK 8
- Maven 3.6+

### 安装依赖

在Maven项目的`pom.xml`中添加`autho-flex-starter`模块依赖：

```xml
<dependency>
    <groupId>com.fuliang</groupId>
    <artifactId>autho-flex-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 基础配置

在`application.yml`中配置AuthoFlex参数（可选）：

```yaml
autho-flex:
  hello-msg: "AuthoFlex 已启动"
  name: "my-project"
  token-prefix: "my-token"
  token-ttl-seconds: 3600  # Token有效期1小时
  dao-type: "redis"  # 存储方式，可选（使用Redis存储需引入autho-flex-redis模块）
  token-override: true  # 重复登录时覆盖旧Token
  enable-interceptor: true # 注解鉴权
```


### 简单使用示例

#### 1. 登录操作

调用者验证用户账号密码匹配后，通过`AfUtil.login(id)`通知框架生成并保存Token：

```java
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginForm form) {
        // 1. 验证账号密码（示例逻辑，实际需查询数据库）
        boolean valid = userService.checkPassword(form.getUsername(), form.getPassword());
        if (!valid) {
            return Result.error("账号或密码错误");
        }

        // 2. 获取用户ID（假设从数据库查询到用户ID为"123"）
        String userId = "123";

        // 3. 调用AfUtil完成登录（框架自动生成并存储Token）
        AfUtil.login(userId);

        // 4. 返回生成的Token（框架自动管理）
        return Result.success("登录成功");
    }
}
```

#### 2. 校验登录状态

在需要认证的接口中，通过`AfUtil.checkLogin()`校验用户是否已登录。未登录时自动抛出`NotLoginException`。

```java
    @GetMapping("/info")
    public Result<UserInfo> getUserInfo() {
        // 1. 校验登录状态（未登录时抛异常，由全局异常处理器捕获返回）
        AfUtil.checkLogin();

        // 2. 获取已登录用户ID
        String userId = AfUtil.getLoginId();

        // 3. 查询用户信息并返回
        UserInfo userInfo = userService.getUserInfo(userId);
        return Result.success(userInfo);
    }
```

#### 3. 登出操作

调用`AfUtil.logout()`清除当前用户的Token：

```java
    @PostMapping("/logout")
    public Result<Void> logout() {
        // 清除当前用户Token
        AfUtil.logout();
        return Result.success("登出成功");
    }
}
```

> **提示**：框架默认使用内存存储Token（`ConcurrentHashMap`），适合单实例应用；若需分布式支持，需引入`autho-flex-redis`模块并在配置中设置`dao-type: redis`。

#### 4. 注解鉴权

支持在Controller类或方法上添加注解，自动校验登录状态、角色或权限。

#### 4.1 `@AfCheckLogin`：校验登录状态

在类或方法上添加`@AfCheckLogin`，强制要求用户登录后才能访问：

```java
@RestController
@RequestMapping("/order")
@AfCheckLogin  // 类级别注解：所有方法需登录后访问
public class OrderController {
    // 无需额外注解，继承类级别校验
    @GetMapping("/list")
    public Result<List<Order>> getOrderList() {
        String userId = AfUtil.getLoginId();
        return Result.success(orderService.getOrdersByUserId(userId));
    }

    // 方法级别可以覆盖类级别注解（若类未加注解时单独使用）
    @PostMapping("/detail")
    @AfCheckLogin
    public Result<Order> getOrderDetail(@RequestParam String orderId) {
        return Result.success(orderService.getOrderDetail(orderId));
    }
}
```

#### 5.2 `@AfCheckRole`：校验用户角色（AND模式）

要求用户同时拥有多个角色（默认`AND`模式）：

```java
@PostMapping("/admin/setting")
@AfCheckRole(value = {"admin", "supervisor"}, mode = AfCheckMode.AND)
public Result<Void> updateAdminSetting(@RequestBody SettingForm form) {
    // 仅同时拥有"admin"和"supervisor"角色的用户可访问
    return Result.success();
}
```

#### 5.3 `@AfCheckPermission`：校验用户权限（OR模式）

用户拥有任意一个权限即可访问（`OR`模式）：

```java
@DeleteMapping("/resource/{id}")
@AfCheckPermission(value = {"resource:delete", "resource:manage"}, mode = AfCheckMode.OR)
public Result<Void> deleteResource(@PathVariable String id) {
    // 拥有"resource:delete"或"resource:manage"权限的用户可访问
    return Result.success();
}
```

> **提示**：
>
> - 注解可同时添加在类和方法上（方法注解优先级高于类注解）。
> - 角色/权限的具体值需通过`AfPermissionHanlder`接口实现（用户需自定义实现类，返回当前用户的角色/权限列表）。
> - 拦截器开关由`AuthoFlexConfig.enableInterceptor`控制（默认`false`，需手动开启）。

## 许可证

本项目采用 [MIT License](https://opensource.org/licenses/MIT) 开源协议。

## 联系与支持

- 维护者：@FuliangTeaSeller（GitHub:[FuliangTeaSeller](https://github.com/FuliangTeaSeller)）
- 问题反馈：请通过GitHub Issues提交（[链接](https://github.com/FuliangTeaSeller/AuthoFlex/issues)）

## 致谢

感谢以下开源项目对本框架的支持：

- [Hutool](https://hutool.cn/)：提供缓存工具类与基础工具方法。

- [Spring Data Redis](https://spring.io/projects/spring-data-redis)：提供Redis集成支持。

  ​      
