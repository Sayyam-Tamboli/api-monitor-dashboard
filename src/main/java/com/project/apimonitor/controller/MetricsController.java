package com.project.apimonitor.controller;


//import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
//import io.micrometer.core.annotation.Counted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.apimonitor.service.UserMetricsService;

@RestController
@RequestMapping("/api/monitor")
public class MetricsController {

	@Autowired
	private MeterRegistry meterRegistry;
	
	private final UserMetricsService userMetricsService;

    public MetricsController(UserMetricsService userMetricsService) {
        this.userMetricsService = userMetricsService;
    }

//    @Timed(value = "api.monitor.getData.timer", description = "Time taken to return getData")
//    @Counted(value = "api.monitor.getData.counter", description = "Times getData endpoint is called")
//    @GetMapping("/data")
//    public String getData() {
//        return "Metrics API response";
//    }
    
    @GetMapping("/data")
    public String getData() {
        Timer timer = meterRegistry.timer("api.monitor.getdata.manual.timer");
        return timer.record(() -> {
            // Simulate processing
            return "Manual timing success";
        });
    }

    @GetMapping("/login")
    public String login() {
        userMetricsService.userLoggedIn();
        return "User logged in. Active users: " + userMetricsService.getActiveUsers();
    }

    @GetMapping("/logout")
    public String logout() {
        userMetricsService.userLoggedOut();
        return "User logged out. Active users: " + userMetricsService.getActiveUsers();
    }
}