package com.ruoyi.system.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置类
 * 配置RestTemplate的连接超时和读取超时
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 创建并配置RestTemplate Bean
     * @return 配置好的RestTemplate实例
     */
    @Bean
    public RestTemplate restTemplate() {
        // 1. 创建简单HTTP请求工厂
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        
        // 2. 设置连接超时为10秒
        factory.setConnectTimeout(10000);
        
        // 3. 设置读取超时为15秒
        factory.setReadTimeout(15000);
        
        // 4. 使用配置好的工厂创建RestTemplate
        return new RestTemplate(factory);
    }
}