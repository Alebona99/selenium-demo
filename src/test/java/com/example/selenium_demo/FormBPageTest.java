package com.example.selenium_demo;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormBPageTest {

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
        webDriver.get("http://localhost:4200/formB");
    }


    /**
     * Test case 1
     * Controllo che la pagina sia stata caricata correttamente
     * e che contenga la stringa Registration
     */
    @Test
    public void isLoaded(){
        Assertions.assertTrue(webDriver.getPageSource().contains("Registration"));
    }


    /**
     * Test case 2
     * Controllo che completando i campi correttamente
     * avvenga la registrazione
     */
    @Test
    public void formB_success(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Registrazione avvenuta"));
    }


    /**
     * Test case 3
     * Controllo che completando correttamente i campi
     * mi venga visualizzato il summary
     */
    @Test
    public void formB_summary(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("summary")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("This is your Summary"));
    }


    /**
     * Test case 4
     * Controllo che completando tutti i campi correttamente
     * mi venga visualizzato il summary e poi la registrazione vada a buon fine
     */
    @Test
    public void formB_summary_submit(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("summary")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("This is your Summary"));
        webDriver.findElement(By.id("close")).click();

        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Registrazione avvenuta"));
    }


    /**
     * Test case 5
     * Controllo che completando tutti i campi senza la checkbox dei termini
     * sia abilitato il bottone login
     */
    @Test
    public void formB_no_terms(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");

        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
    }


    /**
     * Test case 6
     * Controllo che non completando il campo nome
     * mi esca una stringa di errore
     */
    @Test
    public void formB_no_name(){
        webDriver.findElement(By.id("nome")).sendKeys("");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Name is required"));
    }


    /**
     * Test case 7
     * Controllo se mettendo un nome con lunghezza inferiore a quella consentita
     * che mi esca una stringa di errore
     */
    @Test
    public void formB_name_min(){
        webDriver.findElement(By.id("nome")).sendKeys("al");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Name min 4"));
    }


    /**
     * Test case 8
     * Controllo che non completando il campo cognome
     * ecsa una stringa di errore
     */
    @Test
    public void formB_no_surname(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Surname is required"));
    }


    /**
     * Test case 9
     * Controllo che mettendo un cognome inferiore alla lunghezza consentita
     * esca una stringa di errore
     */
    @Test
    public void formB_surname_min(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("v");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P01D332V");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Surname min 2"));
    }


    /**
     * Test case 10
     * Controllo che non inserendo il codice fiscale
     * esca una stringa di errore
     */
    @Test
    public void formB_no_codicef(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Codice Fiscale is required"));
    }


    /**
     * Test case 11
     * Controllo che completando il form ma con un codice fiscale di lunghezza sbagliata
     * esca una stringa di errore
     */
    @Test
    public void formB_codicef_min(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS99P0");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Codice Fiscale must be 16 characters"));
    }


    /**
     * Test case 12
     * Controllo che completando il form con un codice fiscale di formato sbagliato(solo lettere)
     * esca una stringa di errore
     */
    @Test
    public void formB_codicef_wrong(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("AAAAAAAAAAAAAAAA");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Codice Fiscale is invalid"));
    }


    /**
     * Test case 13
     * Controllo che completando il form con un codice fiscale di formato sbagliato (solo numeri)
     * esca una stringa di errore
     */
    @Test
    public void formB_codicef_numeric(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("1234567890123456");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Codice Fiscale is invalid"));
    }


    /**
     * Test case 14
     * Controllo che completando il form con un formato di codice fiscale sbagliato
     * esca ua stringa di errore
     */
    @Test
    public void formB_codicef_alphanumeric(){
        webDriver.findElement(By.id("nome")).sendKeys("Alessio");
        webDriver.findElement(By.id("cognome")).sendKeys("Bonanno");
        webDriver.findElement(By.id("codicef")).sendKeys("BNNLSS4567ERT567");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Codice Fiscale is invalid"));
    }


    /**
     * Test case 15
     * Controllo che non mettendo nulla nel form mi escano le stringhe di errore
     * e che il bottone login sia disabilitato
     */
    @Test
    public void formB_all_wrong(){
        webDriver.findElement(By.id("nome")).sendKeys("");
        webDriver.findElement(By.id("cognome")).sendKeys("");
        webDriver.findElement(By.id("codicef")).sendKeys("");
        webDriver.findElement(By.id("policy")).click();
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Name is required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Surname is required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Codice Fiscale is required"));
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
    }

}
