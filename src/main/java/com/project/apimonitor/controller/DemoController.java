package com.project.apimonitor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/greet")
    @Timed(value = "api.greet.time", description = "Time taken to return greeting")
    public String greet() {
        return "Hello from API!";
    }

    @GetMapping("/data")
    @Timed(value = "api.data.time", description = "Time taken to fetch data")
    public List<String> getData() {
        return List.of("item1", "item2", "item3");
    }

    @GetMapping("/error")
    public String throwError() {
        throw new RuntimeException("Simulated error");
    }
}