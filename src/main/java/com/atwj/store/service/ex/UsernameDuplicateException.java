package com.atwj.store.service.ex;

/**
 * @author 吴先森
 * @description: 当用户进行注册时，可能会因为用户名被占用而导致无法正常注册，此时需要抛出用户名被占用的异常，因此可以设计一个用户名重复的异常类，继承自ServiceException类，并从父类生成子类的五个构造方法。
 * @create 2022-05-15 16:46
 */
public class UsernameDuplicateException extends ServiceException{
    public UsernameDuplicateException() {
        super();
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
