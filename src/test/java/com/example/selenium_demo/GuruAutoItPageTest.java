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

public class GuruAutoItPageTest {

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
        webDriver.get("http://demo.guru99.com/test/autoit.html");
    }


    /**
     * Test case 1
     * Controllo che venga visalizzata correttamente la pagina
     */
    @Test
    public void isLoaded(){
        Assertions.assertTrue(webDriver.getPageSource().contains("Become an Instructor"));
        webDriver.quit();
    }


    /**
     * Test case 2
     * Controllo che la procedura create article avvenga con successo
     * e che si visualizzi la scritta di conferma
     */
    @Test
    public void create_article_success(){

        webDriver.switchTo().frame("JotFormIFrame-72320244964454");
        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("input_5")).sendKeys("/home/alessio/Documenti/link_guru.txt");
        webDriver.findElement(By.id("input_6")).sendKeys("Qualcosa");
        webDriver.findElement(By.id("input_2")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Thank You!"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Your submission has been received."));

    }


    /**
     * Test case 3
     * Controllo che completando il form senza nome mi esca una stringa di errore
     */
    @Test
    public void create_article_no_name(){
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("input_5")).sendKeys("/home/alessio/Documenti/link_guru.txt");
        webDriver.findElement(By.id("input_6")).sendKeys("Qualcosa");
        webDriver.findElement(By.id("input_2")).click();

        String error = webDriver.findElement(By.className("form-error-message")).getText();
        Assertions.assertEquals(error, "This field is required.");
        Assertions.assertTrue(webDriver.getPageSource().contains("There are errors on the form. Please fix them before continuing."));
    }


    /**
     * Test case 4
     * Controllo che completando il form senza email mi esca un messaggio di errore
     */
    @Test
    public void create_article_no_email(){
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_5")).sendKeys("/home/alessio/Documenti/link_guru.txt");
        webDriver.findElement(By.id("input_6")).sendKeys("Qualcosa");
        webDriver.findElement(By.id("input_2")).click();

        String error = webDriver.findElement(By.className("form-error-message")).getText();
        Assertions.assertEquals(error, "This field is required.");
        Assertions.assertTrue(webDriver.getPageSource().contains("There are errors on the form. Please fix them before continuing."));
    }


    /**
     * Test case 5
     * Controllo che completando il form con un email di formato sbagliato
     * mi esca un messaggio di errore
     */
    @Test
    public void create_article_invalid_email(){
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail");
        webDriver.findElement(By.id("input_5")).sendKeys("/home/alessio/Documenti/link_guru.txt");
        webDriver.findElement(By.id("input_6")).sendKeys("Qualcosa");
        webDriver.findElement(By.id("input_2")).click();

        String error = webDriver.findElement(By.className("form-error-message")).getText();
        Assertions.assertEquals(error, "Enter a valid e-mail address");
        Assertions.assertTrue(webDriver.getPageSource().contains("There are errors on the form. Please fix them before continuing."));
    }


    /**
     * Test case 6
     * Controllo che completando il form senza il campo file
     * mi esca un messaggio di errore
     */
    @Test
    public void create_article_no_file(){
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");

        webDriver.findElement(By.id("input_6")).sendKeys("Qualcosa");
        webDriver.findElement(By.id("input_2")).click();

        String error = webDriver.findElement(By.className("form-error-message")).getText();
        Assertions.assertEquals(error, "This field is required.");
        Assertions.assertTrue(webDriver.getPageSource().contains("There are errors on the form. Please fix them before continuing."));
    }


    /**
     * Test case 7
     * Controllo che completando il form con un formato file non consentito
     * mi esca un messaggio di errore
     */
    @Test
    public void create_article_wrong_file(){
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("input_5")).sendKeys("/home/alessio/Documenti/video.mp4");
        webDriver.findElement(By.id("input_6")).sendKeys("Qualcosa");
        webDriver.findElement(By.id("input_2")).click();

        String error = webDriver.findElement(By.className("form-error-message")).getText();
        Assertions.assertEquals(error, "You can only upload following files:\n" +
                "pdf, doc, docx, xls, xlsx, csv, txt, rtf, html, zip, mp3, wma, mpg, flv, avi, jpg, jpeg, png, gif");
        Assertions.assertTrue(webDriver.getPageSource().contains("There are errors on the form. Please fix them before continuing."));
    }


    /**
     * Test case 8
     * Controllo che completando il form senza il campo topic
     * mi esca un messaggio di errore
     */
    @Test
    public void create_article_no_topic(){
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("input_5")).sendKeys("/home/alessio/Documenti/link_guru.txt");
        webDriver.findElement(By.id("input_2")).click();

        String error = webDriver.findElement(By.className("form-error-message")).getText();
        Assertions.assertEquals(error, "This field is required.");
        Assertions.assertTrue(webDriver.getPageSource().contains("There are errors on the form. Please fix them before continuing."));
    }


    /**
     * Test case 9
     * Controllo che usando i bottoni inferiori per cambiare pagina, trovo l'errore di pagina non trovata
     * e i campi completati precedentemente rimangano gli stessi
     */
    @Test
    public void create_article_navigate(){

        webDriver.switchTo().frame("JotFormIFrame-72320244964454");

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");

        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//a[@rel='next']")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Not Found"));

        webDriver.navigate().back();
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");
        Assertions.assertTrue(webDriver.findElement(By.id("input_3")).getAttribute("value").contains("Alessio"));
        Assertions.assertTrue(webDriver.findElement(By.id("input_4")).getAttribute("value").contains("alexiobona99@gmail.com"));

        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//a[@rel='prev']")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Not Found"));

        webDriver.navigate().back();
        webDriver.switchTo().frame("JotFormIFrame-72320244964454");
        Assertions.assertTrue(webDriver.findElement(By.id("input_3")).getAttribute("value").contains("Alessio"));
        Assertions.assertTrue(webDriver.findElement(By.id("input_4")).getAttribute("value").contains("alexiobona99@gmail.com"));
    }


    /**
     * Test case 10
     * Controllo che premendo il bottone create course mi venga visualizzata la pagina con il form,
     * dopo averlo completato controllo che mi venga visualizzata la pagina di registrazione avvenuta
     */
    @Test
    public void create_course(){
        webDriver.findElement(By.id("getjob")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Are there any costs associated with creating courses on Guru99?"));

        webDriver.findElement(By.id("input_3")).sendKeys("Alessio");
        webDriver.findElement(By.id("input_4")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("input_5")).sendKeys("Play");
        webDriver.findElement(By.id("input_6")).sendKeys("Paragrafo");
        webDriver.findElement(By.id("input_7_0")).click();
        webDriver.findElement(By.id("input_8")).sendKeys("/home/alessio/Documenti/Esercizi-11-maggio.doc");
        webDriver.findElement(By.id("input_2")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Thank You!"));
        Assertions.assertTrue(webDriver.findElement(By.tagName("img")).isDisplayed());

    }

    @Test
    public void quit(){
        webDriver.quit();
    }
}
