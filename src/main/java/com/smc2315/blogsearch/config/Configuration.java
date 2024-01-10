package com.smc2315.blogsearch.config;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@org.springframework.context.annotation.Configuration
@EnableRetry
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Configuration {
}
