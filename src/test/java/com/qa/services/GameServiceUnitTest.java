package com.qa.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.HobbyAppApplication;
import com.qa.persistence.domain.Developer;
import com.qa.persistence.domain.Game;
import com.qa.persistence.dto.GameDTO;
import com.qa.persistence.repos.GameRepo;

@SpringBootTest(classes = HobbyAppApplication.class)
public class GameServiceUnitTest {

	@MockBean
	private GameRepo repo;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Autowired
	private GameService service;
	
	private GameDTO mapToDTO(Game game) {
		return this.mapper.map(game, GameDTO.class);
	}
	
	@Test
	public void ConstructorTest() {
		GameService serviceTest = new GameService(repo, mapper);
		
		assertThat(serviceTest).isInstanceOf(GameService.class);
	}
	
	@Test
	public void createTest() {
		Game TEST_GAME = new Game(3L, new Developer (1L, "Activision", new ArrayList<Game>()), "Strategy", "PC", "Starcraft");
		Mockito.when(this.repo.save(Mockito.any(Game.class))).thenReturn(TEST_GAME);
		
		GameDTO result = this.service.createGame(TEST_GAME);
		
		assertEquals(result, mapToDTO(TEST_GAME));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_GAME);
	}
	
	@Test
	public void readByIDTest() {
		Game TEST_GAME = new Game(1L, new Developer (1L, "Activision", new ArrayList<Game>()), "Strategy", "PC", "Starcraft");
		Optional<Game> OptionalGame = Optional.of(TEST_GAME);
		Mockito.when(this.repo.findById(1L)).thenReturn(OptionalGame);
		
		GameDTO result = this.service.readByID(1L);
		
		assertEquals(result, mapToDTO(TEST_GAME));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void readAll() {
		List<Game> games = new ArrayList<>();
		games.add(new Game(1L, new Developer (1L, "Activision", new ArrayList<Game>()), "Shooter", "PC", "CoD"));
		games.add(new Game(2L, new Developer (2L, "Valve", new ArrayList<Game>()), "Puzzle", "Switch", "Swapper"));
		
		List<GameDTO> gamesDTO = new ArrayList<>();
		gamesDTO.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
		gamesDTO.add(new GameDTO(2L, "Puzzle", "Switch", "Swapper"));
		
		Mockito.when(this.repo.findAll()).thenReturn(games);
		
		List<GameDTO> resultList = this.service.readAll();
		
		assertEquals(resultList, gamesDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void update() {
		Game TEST_GAME = new Game(1L, new Developer (2L, "Valve", new ArrayList<Game>()), "Strategy", "PC", "Starcraft");
		Optional<Game> OptionalGame = Optional.of(TEST_GAME);
				
		Game TEST_UPDATED = new Game(1L, new Developer (1L, "Activision", new ArrayList<Game>()), "Simulation", "Xbox", "Sims");
		GameDTO TEST_UPDATED_DTO = new GameDTO(1L, "Simulation", "Xbox", "Sims");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(OptionalGame);
		Mockito.when(this.repo.save(TEST_UPDATED)).thenReturn(TEST_UPDATED);
		
		GameDTO result = this.service.updateGame(1L, TEST_UPDATED);
		
		assertEquals(result, TEST_UPDATED_DTO);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_UPDATED);
	}
	
	@Test
	public void delete() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		boolean result = this.service.deleteGame(1L);
		
		assertEquals(result, true);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	

}
