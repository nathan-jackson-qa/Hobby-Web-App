package com.qa.persistence.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyAppApplication;

@SpringBootTest(classes = HobbyAppApplication.class)
public class DevDTOUnitTest {


	@Test
	public void testNoArgsConstructor () {
		DeveloperDTO empty = new DeveloperDTO();
		assertEquals(empty.getId(), null);
	}
	
	@Test
	public void testGettersSetters() {
		DeveloperDTO TEST_DEV = new DeveloperDTO(1L, "Activision", new ArrayList<GameDTO>());
		
		Long newID = 2L;
		String newName = "Blizzard";
		List<GameDTO> newGames = new ArrayList<GameDTO>();
		newGames.add(new GameDTO());
		
		TEST_DEV.setId(newID);
		TEST_DEV.setName(newName);
		TEST_DEV.setGames(newGames);
		
		assertEquals(newID, TEST_DEV.getId());
		assertEquals(newName, TEST_DEV.getName());
		assertEquals(newGames, TEST_DEV.getGames());
	}
	
	@Test
	public void TestToString() {
		DeveloperDTO TEST_DEV = new DeveloperDTO(1L, "Activision", new ArrayList<GameDTO>());
		String output = "DeveloperDTO [id=1, name=Activision, games=[]]";
		String result = TEST_DEV.toString();

		assertEquals(result, output);
	}
	
	@Test
	public void testEquals() {
		DeveloperDTO TEST_DEV = new DeveloperDTO(1L, "Activision", new ArrayList<GameDTO>());
		DeveloperDTO TEST_DEV2 = new DeveloperDTO(1L, "Activision", new ArrayList<GameDTO>());
		
		assertTrue(TEST_DEV.equals(TEST_DEV2));
	}
	
	@Test 
	public void testNotEquals() {
		DeveloperDTO TEST_DEV = new DeveloperDTO(1L, "Activision", new ArrayList<GameDTO>());
		DeveloperDTO TEST_DEV2 = new DeveloperDTO(2L, "Blizzard", new ArrayList<GameDTO>());
		
		assertFalse(TEST_DEV.equals(TEST_DEV2));
	}
}
