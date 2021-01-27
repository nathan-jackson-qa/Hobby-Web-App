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
import com.qa.persistence.dto.DeveloperDTO;
import com.qa.persistence.dto.GameDTO;
import com.qa.persistence.repos.DeveloperRepo;

@SpringBootTest(classes = HobbyAppApplication.class)
public class DevServiceUnitTest {

	@MockBean
	private DeveloperRepo repo;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Autowired
	private DeveloperService service;
	
	private DeveloperDTO mapToDTO(Developer dev) {
		return this.mapper.map(dev, DeveloperDTO.class);
	}
	
	@Test
	public void ConstructorTest() {
		DeveloperService serviceTest = new DeveloperService(repo, mapper);
		
		assertThat(serviceTest).isInstanceOf(DeveloperService.class);
	}
	
	@Test
	public void create() {
		Developer TEST_DEV = new Developer (3L, "Valve", new ArrayList<Game>());
		Mockito.when(this.repo.save(Mockito.any(Developer.class))).thenReturn(TEST_DEV);
		
		DeveloperDTO result = this.service.createDev(TEST_DEV);
		
		assertEquals(result, mapToDTO(TEST_DEV));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_DEV);
	}
	
	@Test
	public void readByID() {
		Developer TEST_DEV = new Developer (3L, "Valve", new ArrayList<Game>());
		Optional<Developer> OptionalDev = Optional.of(TEST_DEV);
		Mockito.when(this.repo.findById(1L)).thenReturn(OptionalDev);
		
		DeveloperDTO result = this.service.readById(1L);
		
		assertEquals(result, mapToDTO(TEST_DEV));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void readAll() {
		List<Game> games =  new ArrayList<Game>();
		games.add(new Game(1L, new Developer(), "Shooter", "PC", "CoD"));
		List<Developer> devs = new ArrayList<>();
		devs.add(new Developer(1L, "Activision", games));

	
		List<GameDTO> gamesDTO =  new ArrayList<GameDTO>();
		gamesDTO.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
		List<DeveloperDTO> devsDTO = new ArrayList<>();
		devsDTO.add(new DeveloperDTO(1L, "Activision", gamesDTO));


		Mockito.when(this.repo.findAll()).thenReturn(devs);
		
		List<DeveloperDTO> resultList = this.service.readAll();
		
		assertEquals(resultList, devsDTO);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void update() {
	Developer TEST_DEV = new Developer(1L, "Valve", new ArrayList<Game>());
	Optional<Developer> OptionalDev = Optional.of(TEST_DEV);
	
	List<Game> games =  new ArrayList<Game>();
	games.add(new Game(1L, new Developer(), "Shooter", "PC", "CoD"));
	List<GameDTO> gamesDTO =  new ArrayList<GameDTO>();
	gamesDTO.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
	
	
	Developer UPDATE_DEV = new Developer(1L, "Bethesda", games);
	DeveloperDTO UPDATE_DEVDTO = new DeveloperDTO(1L, "Bethesda", gamesDTO);
	
	Mockito.when(this.repo.findById(1L)).thenReturn(OptionalDev);
	Mockito.when(this.repo.save(UPDATE_DEV)).thenReturn(UPDATE_DEV);
	
	DeveloperDTO result = this.service.updateDev(1L, UPDATE_DEV);
	
	assertEquals(result, UPDATE_DEVDTO);
	Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	Mockito.verify(this.repo, Mockito.times(1)).save(UPDATE_DEV);
	}
	
	@Test
	public void delete() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		boolean result = this.service.removeDev(1L);
		
		assertEquals(result, true);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}

}
