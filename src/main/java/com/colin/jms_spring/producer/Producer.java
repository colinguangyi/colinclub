package com.colin.jms_spring.producer;

/**
 * @author zhaolz
 * @create 2017-08-23
 */
public interface Producer {
    void sendMessage(final String msg);
}
