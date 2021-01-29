package com.qa.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public void testNavigation() {
		driver.get("http://localhost:8080/");
		WebElement nav = driver.findElement(By.xpath("/html/body/nav/button"));
		nav.click();
		WebElement link = driver.findElement(By.xpath("//a[@href='developer.html']"));
		link.click();
	}
//	
//	@Test
//	public void gamePage() {
//		TestGamePage gamePage = PageFactory.initElements(driver, TestGamePage.class);
//		gamePage.testCreate();
//		assertTrue(gamePage.createFeedback.getText().contains("Title: Sudoku, Genre: Puzzle, Platform: PC, Developer: Activision"));
//	
//		gamePage.testReadID();
//		assertEquals("Game ID: 1, Title: Call of Duty, Genre: First Person Shooter, Platform: PC", gamePage.readFeedback.getText());
//	
//		gamePage.testReadAll();
//		assertTrue(gamePage.readAllFeedback.getText().contains("Game ID: 1, Title: Call of Duty, Genre: First Person Shooter, Platform: PC"));
//		
//		gamePage.testUpdate();
//		assertEquals("Update Successful", gamePage.updateFeedback.getText());
//		
//		gamePage.testDelete();
//		assertEquals("Item Deleted!", gamePage.deleteFeedback.getText());
//		
//	}
//	
	
}
