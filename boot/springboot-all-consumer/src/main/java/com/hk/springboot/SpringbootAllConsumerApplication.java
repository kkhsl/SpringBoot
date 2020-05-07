package com.hk.springboot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration //开启dubbo自动配置 识别dobbo @service
public class SpringbootAllConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllConsumerApplication.class, args);
    }

}
