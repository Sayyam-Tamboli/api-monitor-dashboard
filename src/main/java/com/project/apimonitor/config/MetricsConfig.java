package com.project.apimonitor.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    public MetricsConfig(MeterRegistry registry) {
        registry.config().commonTags("application", "api-monitor-demo");
    }
}