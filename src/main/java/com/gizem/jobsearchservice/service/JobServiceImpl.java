package com.gizem.jobsearchservice.service;

import com.gizem.jobsearchservice.entity.Job;
import com.gizem.jobsearchservice.entity.JobSearchHistory;
import com.gizem.jobsearchservice.repository.JobRepository;
import com.gizem.jobsearchservice.repository.JobSearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSearchHistoryRepository historyRepository;

    @Override
    public Page<Job> search(String title, String city, String country, String workingType, String userId, Pageable pageable) {
        if (userId != null && (title != null || city != null)) {
            JobSearchHistory history = new JobSearchHistory();
            history.setUserId(userId);
            history.setTitle(title);
            history.setCity(city);


            history.setTimestamp(LocalDateTime.now());
            historyRepository.save(history);
        }

        return jobRepository
                .findByTitleContainingIgnoreCaseAndCityContainingIgnoreCaseAndCountryContainingIgnoreCaseAndWorkingTypeContainingIgnoreCase(
                        title != null ? title : "",
                        city != null ? city : "",
                        country != null ? country : "",
                        workingType != null ? workingType : "",
                        pageable
                );
    }

    @Override
    public List<Job> autocompleteByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Job> autocompleteByCity(String city) {
        return jobRepository.findByCityContainingIgnoreCase(city);
    }

    @Override
    public Page<Job> getJobsByCity(String city, Pageable pageable) {
        return jobRepository.findByCityContainingIgnoreCase(city, pageable);
    }

    @Override
    public List<JobSearchHistory> getSearchHistoryByUserId(String userId) {
        return historyRepository.findByUserIdOrderByTimestampDesc(userId);
    }
    @Override
    public void saveSearch(String userId, String title, String city) {
        if (userId != null && (title != null || city != null)) {
            JobSearchHistory history = new JobSearchHistory();
            history.setUserId(userId);
            history.setTitle(title);
            history.setCity(city);
            history.setTimestamp(LocalDateTime.now());
            historyRepository.save(history);
        }
    }
}
