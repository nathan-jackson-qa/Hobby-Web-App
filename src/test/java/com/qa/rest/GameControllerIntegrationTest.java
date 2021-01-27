package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyAppApplication;
import com.qa.persistence.domain.Developer;
import com.qa.persistence.domain.Game;
import com.qa.persistence.dto.GameDTO;

@SpringBootTest(classes = HobbyAppApplication.class)
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "dev")
public class GameControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private final int testID = 1;
	
	private GameDTO mapToDTO(Game game) {
		return this.mapper.map(game, GameDTO.class);
	}
	
	// ====================================
	// TESTS
	// ====================================
	
	// CREATE
	@Test
	public void createGame() throws Exception{
		Game TEST_GAME = new Game(3L, new Developer (1L, "Activision", new ArrayList<Game>()), "Strategy", "PC", "Starcraft");
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.POST, "/game/create/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_GAME))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_GAME)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	// READ
	@Test
	public void readOne() throws Exception{
		GameDTO TEST_GAME = new GameDTO(1L, "Shooter", "PC", "CoD");
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.GET, "/game/read/"+testID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_GAME));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void readAll() throws Exception{
		List<GameDTO> games = new ArrayList<>();
		games.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
		games.add(new GameDTO(2L, "Puzzle", "Switch", "Swapper"));
		
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.GET, "/game/readAll/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(games));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	// UPDATE
	
	@Test
	public void update() throws Exception{
		Game TEST_GAME = new Game(1L, new Developer(2L, "Valve", new ArrayList<Game>()), "Racing", "PS4", "Gran Turismo");
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/game/update/"+testID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_GAME))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();

		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
	
	// DELETE
	@Test
	public void delete() throws Exception{
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/game/delete/"+testID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
}
