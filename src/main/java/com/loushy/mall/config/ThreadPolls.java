package com.loushy.mall.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component  //启动时被扫描
public class ThreadPolls implements AsyncConfigurer {

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        //设置核心线程数
        threadPool.setCorePoolSize(4);

        //设置最大线程数
        threadPool.setMaxPoolSize(4);

        //线程池所使用的缓冲队列
//        threadPool.setQueueCapacity(4);

        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);

        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(60);

        //  线程名称前缀
        threadPool.setThreadNamePrefix("MyAsync-");

        // 初始化线程
        threadPool.initialize();

        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
