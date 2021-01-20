package com.spring.mvc.RQB.repository;

import com.spring.mvc.RQB.entities.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "watchRepository")
public interface WatchRepository extends JpaRepository<Watch, Integer>{

}