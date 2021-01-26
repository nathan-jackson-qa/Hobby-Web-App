package com.qa.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.HobbyAppApplication;

@SpringBootTest(classes = HobbyAppApplication.class)
public class GameUnitTest {

	@Test
	public void testNoArgsConstructor() {
		Game empty = new Game();
		assertEquals(empty.getId(), null);
	}
	
	@Test
	public void testGettersAndSetters() {
		Game TEST_GAME = new Game(10L, 10, "Change", "Change", "Change");
		
		Long id = 1L;
		int devID = 1;
		String genre = "Strategy";
		String platform = "PC";
		String title = "Starcraft";
		
		TEST_GAME.setId(id);
		TEST_GAME.setDeveloperID(devID);
		TEST_GAME.setGenre(genre);
		TEST_GAME.setPlatform(platform);
		TEST_GAME.setTitle(title);		
		
		assertEquals(id, TEST_GAME.getId());
		assertEquals(devID, TEST_GAME.getDeveloperID());
		assertEquals(genre, TEST_GAME.getGenre());
		assertEquals(platform, TEST_GAME.getPlatform());
		assertEquals(title, TEST_GAME.getTitle());
	}
	
	@Test
	public void testToString() {
		Game TEST_GAME = new Game(1L, 1, "Shooter", "PC", "CoD");
		String output = "Game(id=1, developerID=1, genre=Shooter, platform=PC, title=CoD)";
		
		String result = TEST_GAME.toString();
		
		assertEquals(result, output);
	}
	
	@Test
	public void testEquals() {
		Game TEST_GAME = new Game(1L, 1, "Shooter", "PC", "CoD");
		Game TEST_GAME2 = new Game(1L, 1, "Shooter", "PC", "CoD");
		
		assertTrue(TEST_GAME.equals(TEST_GAME2));
	}
	
	@Test
	public void testNotEquals() {
		Game TEST_GAME = new Game(1L, 1, "Shooter", "PC", "CoD");
		Game TEST_GAME2 = new Game(2L, 1, "Shooter", "PC", "CoD");
		
		assertFalse(TEST_GAME.equals(TEST_GAME2));
	}

	
}
