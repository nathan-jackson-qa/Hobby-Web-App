package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Long>{

}
