package com.gizem.jobsearchservice.repository;

import com.gizem.jobsearchservice.entity.JobSearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSearchHistoryRepository extends MongoRepository<JobSearchHistory, String> {
    List<JobSearchHistory> findByUserIdOrderByTimestampDesc(String userId);
}
