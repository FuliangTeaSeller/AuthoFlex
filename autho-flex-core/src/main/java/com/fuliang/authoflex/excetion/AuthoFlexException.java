package com.fuliang.authoflex.excetion;

public class AuthoFlexException extends RuntimeException {
    // 无参构造（默认异常）
    public AuthoFlexException() {
        super();
    }

    // 仅包含错误消息
    public AuthoFlexException(String message) {
        super(message);
    }

    // 包含错误消息和原因（支持异常链）
    public AuthoFlexException(String message, Throwable cause) {
        super(message, cause);
    }

    // 仅包含原因（用于包装其他异常）
    public AuthoFlexException(Throwable cause) {
        super(cause);
    }
}