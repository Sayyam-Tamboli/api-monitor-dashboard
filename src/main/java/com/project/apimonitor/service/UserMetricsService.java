package com.project.apimonitor.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserMetricsService {

    private final AtomicInteger activeUsers = new AtomicInteger(0);

    public UserMetricsService(MeterRegistry registry) {
        registry.gauge("api.monitor.active.users", activeUsers);
    }

    public void userLoggedIn() {
        activeUsers.incrementAndGet();
    }

    public void userLoggedOut() {
        activeUsers.decrementAndGet();
    }

    public int getActiveUsers() {
        return activeUsers.get();
    }
}