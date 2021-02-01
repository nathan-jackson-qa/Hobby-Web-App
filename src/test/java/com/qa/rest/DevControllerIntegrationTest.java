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
import com.qa.persistence.dto.DeveloperDTO;
import com.qa.persistence.dto.GameDTO;

@SpringBootTest(classes = HobbyAppApplication.class)
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "dev")
public class DevControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private final int testID = 1;
	
	private DeveloperDTO mapToDTO(Developer dev) {
		return this.mapper.map(dev, DeveloperDTO.class);
	}
	
	@Test
	public void createGame() throws Exception{
		Developer TEST_DEV = new Developer (3L, "Valve", new ArrayList<Game>());
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.POST, "/developer/create/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_DEV))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_DEV)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void readOne() throws Exception{
		List<GameDTO> games =  new ArrayList<GameDTO>();
		games.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
		DeveloperDTO TEST_DEV = new DeveloperDTO(1L, "Activision", games);
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.GET, "/developer/read/"+testID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_DEV));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void readAll() throws Exception{
		List<GameDTO> games1 =  new ArrayList<GameDTO>();
		games1.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
		List<GameDTO> games2 =  new ArrayList<GameDTO>();
		games2.add(new GameDTO(2L, "Puzzle", "Switch", "Swapper"));

		List<DeveloperDTO> devs = new ArrayList<>();
		DeveloperDTO dev1 = new DeveloperDTO(1L, "Activision", games1);
		DeveloperDTO dev2 = new DeveloperDTO(2L, "Blizzard", games2);
		devs.add(dev1);
		devs.add(dev2);
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.GET, "/developer/readAll/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(devs));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void update() throws Exception{
		List<GameDTO> games =  new ArrayList<GameDTO>();
		games.add(new GameDTO(1L, "Shooter", "PC", "CoD"));
		
		DeveloperDTO TEST_DEV = new DeveloperDTO(1L, "New Dev", games);
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/developer/update/"+testID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_DEV))
				.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_DEV));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void delete() throws Exception {
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/developer/delete/"+testID);

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
}
