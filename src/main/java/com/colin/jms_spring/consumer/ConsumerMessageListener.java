package com.colin.jms_spring.consumer;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author zhaolz
 * @create 2017-08-23
 */
public class ConsumerMessageListener implements javax.jms.MessageListener{
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage)message;
        try {
            System.out.println("接收消息:" + msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
