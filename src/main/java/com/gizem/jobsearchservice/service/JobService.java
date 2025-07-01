package com.gizem.jobsearchservice.service;
import com.gizem.jobsearchservice.entity.Job;
import com.gizem.jobsearchservice.entity.JobSearchHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    Page<Job> search(String title, String city, String country, String workingType, String userId, Pageable pageable);
    List<Job> autocompleteByTitle(String input);
    List<Job> autocompleteByCity(String input);
    Page<Job> getJobsByCity(String city, Pageable pageable);
    List<JobSearchHistory> getSearchHistoryByUserId(String userId);
    void saveSearch(String userId, String title, String city);
}
