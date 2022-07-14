package com.atwj.store.service.ex;

/**
 * @author 吴先森
 * @description: 在用户进行注册时，会执行数据库的INSERT操作，该操作也是有可能失败的。则创建异常类，继承自ServiceException类，并从父类生成子类的5个构造方法。
 * @create 2022-05-15 16:47
 */
public class InsertException extends ServiceException{
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
