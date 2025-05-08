package com.example.customer;

public class CustomerServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public enum ExceptionType {
        DuplicateCustomerUnid("BlogServiceException.Duplicate"),
        Undefined("Undefined");

        private final String messageKey;

        ExceptionType(String messageKey) {
            this.messageKey = messageKey;
        }

        public String getMessageKey() {
            return messageKey;
        }
    }

    private final ExceptionType exceptionType;

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    // 預設建構子
    public CustomerServiceException() {
        super();
        this.exceptionType = ExceptionType.Undefined;
    }

    // 最常用：直接指定錯誤類型與訊息
    public CustomerServiceException(ExceptionType exType, String message) {
        super(message);
        this.exceptionType = exType;
    }

    // 包含 cause 的版本
    public CustomerServiceException(ExceptionType exType, String message, Throwable cause) {
        super(message, cause);
        this.exceptionType = exType;
    }

    // 其他不指定 type 預設為 Undefined
    public CustomerServiceException(String message, Throwable cause) {
        super(message, cause);
        this.exceptionType = ExceptionType.Undefined;
    }

    public CustomerServiceException(Throwable cause) {
        super(cause);
        this.exceptionType = ExceptionType.Undefined;
    }

}
	

