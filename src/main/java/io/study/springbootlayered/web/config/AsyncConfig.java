package io.study.springbootlayered.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 30;
    private static final int QUEUE_CAPACITY = 100;
    private static final boolean WAIT_TASK_COMPLETE = true;
    private static final int AWAIT_TERMINATION_SECONDS = 30;
    private static final String THREAD_NAME_PREFIX = "executor-";

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE); // 동시에 실행 할 기본 스레드의 수를 설정
        executor.setMaxPoolSize(MAX_POOL_SIZE); // 스레드풀의 사용할 수 있는 최대 스레드 수를 설정
        executor.setQueueCapacity(QUEUE_CAPACITY); // 스레드풀 executor의 작업 큐의 크기를 설정
        executor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS); // 최대 대기 타임 아웃 설정
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX); // 스레드의 이름 설정

        // true 설정시 어플리케이션 종료 요청시 queue에 남아 있는 모든 작업들이 완료될 때까지 기다린 후 종료
        executor.setWaitForTasksToCompleteOnShutdown(WAIT_TASK_COMPLETE);

        executor.initialize();

        return executor;
    }
}
