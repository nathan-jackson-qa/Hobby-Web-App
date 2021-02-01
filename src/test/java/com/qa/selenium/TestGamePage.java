package com.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TestGamePage {

	private WebDriver driver;
	
	@FindBy(id="title")
	private WebElement createTitle;	
	@FindBy(name="dev")
	private WebElement createDev;
	@FindBy(id="create")
	private WebElement createButton;
	
	@FindBy(id="gameID")
	private WebElement readValue;
	@FindBy(id="readID")
	private WebElement readButton;
	
	@FindBy(id="readAll")
	private WebElement readAllButton;
	
	@FindBy(id="updateID")
	private WebElement updateId;
	@FindBy(id="updTitle")
	private WebElement updTitle;	
	@FindBy(id="updDev")
	private WebElement updDev;
	@FindBy(id="update")
	private WebElement updateButton;
	
	@FindBy(id="deleteID")
	private WebElement deleteID;
	@FindBy(id="delete")
	private WebElement delete;
	
	public WebElement createFeedback;
	public WebElement readFeedback;
	public WebElement readAllFeedback;
	public WebElement updateFeedback;
	public WebElement deleteFeedback;
	
	
	public TestGamePage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://localhost:8080/");
	}
	
	public void testCreate() throws InterruptedException {
		Select createGenre = new Select(driver.findElement(By.id("genre")));	
		Select createPlatform = new Select(driver.findElement(By.id("platform")));	
		createTitle.sendKeys("Sudoku");
		createGenre.selectByVisibleText("Puzzle");
		createDev.sendKeys("1");
		createPlatform.selectByVisibleText("PC");
		createButton.click();
		Thread.sleep(500);
		createFeedback = driver.findElement(By.id("createFeedback"));
	}
	
	public void testReadID() throws InterruptedException {
		readValue.sendKeys("1");
		readButton.click();		
		Thread.sleep(500);
		readFeedback = driver.findElement(By.id("readDetails"));
	}
	
	public void testReadAll() throws InterruptedException {
		readAllButton.click();
		Thread.sleep(500);
		readAllFeedback = driver.findElement(By.id("readAllDetails"));
	}
	
	
	public void testUpdate() throws InterruptedException {
		Select updGenre = new Select(driver.findElement(By.id("updGenre")));	
		Select updPlat = new Select(driver.findElement(By.id("updPlat")));
		updGenre.selectByVisibleText("Racing");
		updPlat.selectByVisibleText("PS5");
		updateId.sendKeys("3");
		updTitle.sendKeys("Forza");
		updDev.sendKeys("2");
		updateButton.click();
		
		Thread.sleep(500);
		readAllFeedback = driver.findElement(By.id("updateFeedback"));
	}
	
	public void testDelete() throws InterruptedException {
		deleteID.sendKeys("3");
		delete.click();
		Thread.sleep(500);
		readAllFeedback = driver.findElement(By.id("deleteFeedback"));
	}
	
	
}
