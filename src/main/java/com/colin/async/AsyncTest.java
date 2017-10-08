package com.colin.async;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhaolz
 * @create 2017-08-24
 */
@Component
public class AsyncTest {

    /**
     * 无返回值的异步，相当于多起一个线程
     * @param flag
     */
    @Async
    public void async1(String flag){
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + "----" + flag);
        }
    }

    /**
     * 有返回值的异步处理
     * @param flag
     * @return
     */
    @Async
    public Future<String> async2(String flag){
        System.out.println(flag);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>(flag);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        AsyncTest test1 = (AsyncTest)context.getBean("asyncTest");

//        //无返回值的异步测试
//        test1.async1("111111");
//        for(int i = 0; i < 100; i++){
//            System.out.println("22222222222222");
//        }

        //有返回值的异步测试
        Future<String> result = test1.async2("3333333333");
        int flat = 0;
        while(true){
            if(result.isDone()){
                System.out.println("done result:" + result.get());
                break;
            }
//            if(flat == 1){
//                continue;
//            }
//            System.out.println("4444444");
//            flat = 1;
        }
        System.exit(0);
    }
}
