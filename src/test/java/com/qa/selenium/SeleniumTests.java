package com.qa.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


public class SeleniumTests {
	
	private static WebDriver driver;
	
	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	@Disabled
	public void gamePage() throws InterruptedException {
		TestGamePage gamePage = PageFactory.initElements(driver, TestGamePage.class);
		gamePage.testCreate();
		assertTrue(gamePage.createFeedback.getText().contains("Title: Sudoku, Genre: Puzzle, Platform: PC, Developer: Activision"));
	
		gamePage.testReadID();
		assertEquals("Game ID: 1, Title: Call of Duty, Genre: First Person Shooter, Platform: PC", gamePage.readFeedback.getText());
	
		gamePage.testReadAll();
		assertTrue(gamePage.readAllFeedback.getText().contains("Game ID: 1, Title: Call of Duty, Genre: First Person Shooter, Platform: PC"));
		
		gamePage.testUpdate();
		assertEquals("Update Successful", gamePage.updateFeedback.getText());
		
		gamePage.testDelete();
		assertEquals("Item Deleted!", gamePage.deleteFeedback.getText());
	}
	
	@Test
	@Disabled
	public void devPage() throws InterruptedException {
		TestDevPage devPage = PageFactory.initElements(driver,TestDevPage.class);
		
		devPage.testCreate();
		assertTrue(devPage.createFeedback.getText().contains(" Name: Best developer"));
		
		devPage.testReadID();
		assertTrue( devPage.readFeedback.getText().contains("Developer ID: 1, Name: Activision Game 1: Call of Duty"));
		
		devPage.testReadAll();
		assertTrue(devPage.readAllFeedback.getText().contains("Developer ID: 1, Name: Activision Game 1: Call of Duty"));
		
		devPage.testUpdate();
		assertEquals("Update Successful", devPage.updateFeedback.getText());
		
		devPage.testDelete();
		assertEquals("Item Deleted!", devPage.deleteFeedback.getText());
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
	}
	
}
