package com.hkk.springboot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration //开启dubbo自动配置 识别dobbo @service
public class SpringbootAllProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllProviderApplication.class, args);
    }

}
