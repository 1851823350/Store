package com.atwj.store.service.ex;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-24 21:47
 */
public class CartNotExistException extends ServiceException{
    public CartNotExistException() {
        super();
    }

    public CartNotExistException(String message) {
        super(message);
    }

    public CartNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotExistException(Throwable cause) {
        super(cause);
    }

    protected CartNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
