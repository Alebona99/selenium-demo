package com.example.selenium_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GuruDeleteCustomerPageTest {

    MainPage mainPage = new MainPage();
    static WebDriver webDriver= null;

    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        webDriver = new ChromeDriver(chromeOptions);

    }

    @BeforeEach
    public void setUp() {
        webDriver.get("http://demo.guru99.com/test/delete_customer.php");
    }


    /**
     * Test case 1
     * Controllo che la pagina sia caricata correttamente
     */
    @Test
    public void isLoaded(){
        Assertions.assertTrue(webDriver.getPageSource().contains("Customer ID"));
    }


    /**
     * Test case 2
     * Controllo che la procedura di delete customer vada a buon fine
     * e che venga visualizzata alla fine correttamente la pagina
     */
    @Test
    public void delete_success(){
        WebElement input = webDriver.findElement(By.name("cusid"));
        WebElement submit = webDriver.findElement(By.name("submit"));

        input.sendKeys("1234");
        submit.click();

        Assertions.assertTrue(webDriver.switchTo().alert().getText().contains("Do you really want to delete this Customer?"));
        webDriver.switchTo().alert().accept();

        Assertions.assertTrue(webDriver.switchTo().alert().getText().contains("Customer Successfully Delete!"));
        webDriver.switchTo().alert().accept();

        Assertions.assertTrue(webDriver.findElement(By.xpath("//h2[@class='barone']")).getText().equals("Guru99 Bank"));
    }


    /**
     * Test case 3
     * Controllo che premendo il tasto reset venga cancellato il contenuto dell'input
     */
    @Test
    public void delete_reset(){
        WebElement input = webDriver.findElement(By.name("cusid"));
        WebElement reset = webDriver.findElement(By.name("res"));

        input.sendKeys("456");
        reset.click();

        Assertions.assertTrue(input.getText().equals(""));
    }


    /**
     * Test case 4
     * Controllo che la procedura di delete customer mi visualizzi un alert e che rifiutando si visualizzi la pagina
     */
    @Test
    public void delete_not(){
        WebElement input = webDriver.findElement(By.name("cusid"));
        WebElement submit = webDriver.findElement(By.name("submit"));

        input.sendKeys("89989");
        submit.click();

        Assertions.assertTrue(webDriver.switchTo().alert().getText().contains("Do you really want to delete this Customer?"));
        webDriver.switchTo().alert().dismiss();

        Assertions.assertTrue(webDriver.findElement(By.xpath("//h2[@class='barone']")).getText().equals("Guru99 Bank"));

    }
}
