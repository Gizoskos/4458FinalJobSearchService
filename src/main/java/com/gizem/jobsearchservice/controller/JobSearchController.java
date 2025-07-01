package com.gizem.jobsearchservice.controller;

import com.gizem.jobsearchservice.dto.SearchRequest;
import com.gizem.jobsearchservice.entity.Job;
import com.gizem.jobsearchservice.entity.JobSearchHistory;
import com.gizem.jobsearchservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/jobs")
public class JobSearchController {
    @Autowired private JobService service;

    @GetMapping("/search")
    public Page<Job> search(@RequestParam(required = false) String title,
                            @RequestParam(required = false) String city,
                            @RequestParam(required = false) String country,
                            @RequestParam(required = false) String workingType,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String userId) {
        service.saveSearch(userId, title, city);
        Pageable pageable = PageRequest.of(page, size);
        return service.search(title, city, country, workingType, userId, pageable);
    }

    @GetMapping("/autocomplete/title")
    public List<Job> autocompleteByTitle(@RequestParam String input) {
        return service.autocompleteByTitle(input);
    }

    @GetMapping("/autocomplete/city")
    public List<Job> autocompleteByCity(@RequestParam String input) {
        return service.autocompleteByCity(input);
    }

    @GetMapping("/history")
    public List<JobSearchHistory> history(@RequestParam String userId) {
        return service.getSearchHistoryByUserId(userId);
    }

    @GetMapping("/by-city")
    public Page<Job> jobsByCity(@RequestParam String city,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        return service.getJobsByCity(city, PageRequest.of(page, size));
    }
}
