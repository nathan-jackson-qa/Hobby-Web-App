package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.persistence.domain.Developer;

public interface DeveloperRepo extends JpaRepository<Developer, Long>{

}
