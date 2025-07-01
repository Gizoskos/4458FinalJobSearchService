package com.gizem.jobsearchservice.repository;

import com.gizem.jobsearchservice.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByCityContainingIgnoreCase(String city);
    Page<Job> findByTitleContainingIgnoreCaseAndCityContainingIgnoreCaseAndCountryContainingIgnoreCaseAndWorkingTypeContainingIgnoreCase(
            String title, String city, String country, String workingType, Pageable pageable);

    /*Page<Job> findByTitleContainingIgnoreCaseAndCityContainingIgnoreCase(
            String title, String city, Pageable pageable);*/

   /* Page<Job> findByTitleContainingIgnoreCase(String title, Pageable pageable);*/

    Page<Job> findByCityContainingIgnoreCase(String city, Pageable pageable);

    /*Page<Job> findByCountryContainingIgnoreCaseContainingIgnoreCaseAndWorkingTypeContainingIgnoreCase(
            String country, String workingType, Pageable pageable);*/
}
