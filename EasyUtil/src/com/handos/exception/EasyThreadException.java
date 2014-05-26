package com.handos.exception;

/**
 * Created by jimmylee on 5/10/14.
 */
public class EasyThreadException  extends  Exception{
    public EasyThreadException(String s) {
        super(s);
    }

    public EasyThreadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EasyThreadException(Throwable throwable) {
        super(throwable);
    }
}
