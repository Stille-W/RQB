package com.spring.mvc.RQB.repository;

import com.spring.mvc.RQB.entities.RQB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "RQBRepository")
public interface RQBRepository extends JpaRepository<RQB, Integer>{

}