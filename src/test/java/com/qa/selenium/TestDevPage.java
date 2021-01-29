package com.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestDevPage {

	private WebDriver driver;
	
	@FindBy(id="name")
	private WebElement createName;	
	@FindBy(id="create")
	private WebElement createButton;
	
	@FindBy(id="devID")
	private WebElement readValue;
	@FindBy(id="readID")
	private WebElement readButton;
	
	@FindBy(id="readAll")
	private WebElement readAllButton;
	
	@FindBy(id="updateID")
	private WebElement updateId;
	@FindBy(id="updName")
	private WebElement updName;	
	@FindBy(xpath="/html/body/div/div[5]/div[3]/div/button")
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
	
	public TestDevPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get("http://localhost:8080/developer.html");
	}
	
	public void testCreate() throws InterruptedException {
		createName.sendKeys("Best developer");
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
		updateId.sendKeys("3");
		updName.sendKeys("Changed Dev");
		Thread.sleep(500);
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
