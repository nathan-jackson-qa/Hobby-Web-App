package com.qa.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyAppApplication;

@SpringBootTest(classes = HobbyAppApplication.class)
public class DeveloperUnitTest {

	@Test
	public void testNoArgsConstructor () {
		Developer empty = new Developer();
		assertEquals(empty.getId(), null);
	}
	
	@Test
	public void testGettersSetters() {
		Developer TEST_DEV = new Developer(1L, "Activision", new ArrayList<Game>());
		
		Long newID = 2L;
		String newName = "Blizzard";
		List<Game> newGames = new ArrayList<Game>();
		newGames.add(new Game());
		
		TEST_DEV.setId(newID);
		TEST_DEV.setName(newName);
		TEST_DEV.setGames(newGames);
		
		assertEquals(newID, TEST_DEV.getId());
		assertEquals(newName, TEST_DEV.getName());
		assertEquals(newGames, TEST_DEV.getGames());
	}
	
	@Test
	public void TestToString() {
		Developer TEST_DEV = new Developer(1L, "Activision", new ArrayList<Game>());
		String output = "Developer [id=1, name=Activision, games=[]]";
		String result = TEST_DEV.toString();
		
		assertEquals(result, output);
	}
	
	@Test
	public void testEquals() {
		Developer TEST_DEV = new Developer(1L, "Activision", new ArrayList<Game>());
		Developer TEST_DEV2 = new Developer(1L, "Activision", new ArrayList<Game>());
		
		assertTrue(TEST_DEV.equals(TEST_DEV2));
	}
	
	@Test 
	public void testNotEquals() {
		Developer TEST_DEV = new Developer(1L, "Activision", new ArrayList<Game>());
		Developer TEST_DEV2 = new Developer(2L, "Blizzard", new ArrayList<Game>());
		
		assertFalse(TEST_DEV.equals(TEST_DEV2));
	}
}
