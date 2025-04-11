package cn.dev33.satoken.spring;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC 相关操作工具类，快速获取当前会话的 HttpServletRequest、HttpServletResponse 对象
 *
 * @author click33
 * @since 1.19.0
 */
public class SpringMVCUtil {

    private SpringMVCUtil() {
    }

    /**
     * 获取当前会话的 request 对象
     * @return request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null) {
            throw new RuntimeException("非 web 上下文无法获取 HttpServletRequest");
        }
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取当前会话的 response 对象
     * @return response
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null) {
            throw new RuntimeException("非 web 上下文无法获取 HttpServletResponse");
        }
        return servletRequestAttributes.getResponse();
    }

    /**
     * 判断当前是否处于 Web 上下文中
     * @return /
     */
    public static boolean isWeb() {
        return RequestContextHolder.getRequestAttributes() != null;
    }

}

