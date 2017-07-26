package ru.tester.webdriver;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    // data for registration
    private static final String RANDOM_STRING = "TEST" + getRandomString(10);
    private static final String FIRST_NAME = RANDOM_STRING;
    private static final String LAST_NAME = RANDOM_STRING;
    private static final String MARITAL_STATUS = "(//input[@name='radio_4[]'])[2]";
    private static final String HOBBY = "(//input[@name='checkbox_5[]'])[2]";
    private static final String COUNTRY = "United States";
    private static final String MONTH = "11";
    private static final String DAY = "29";
    private static final String YEAR = "1998";
    private static final String PHONE_NUMBER = "12345678910";
    private static final String USER_NAME = RANDOM_STRING;
    private static final String EMAIL = RANDOM_STRING + "@rrr.ru";
    private static final String PATH_TO_PROFILE_PICTURE = System.getProperty("user.dir") + "\\src\\test\\resources\\index.png";
    private static final String ABOUT_YOURSELF = "About yourself";
    private static final String PASSWORD = "12345678";

    public static String getRandomString(int length) {
        String validChars = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++)
            randomString.append(validChars.charAt((int) (Math.random() * validChars.length())));
        return String.valueOf(randomString);

    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://demoqa.com/registration/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testRegistrationPositive() throws Exception {
        driver.get(baseUrl + "/registration/");
        driver.findElement(By.id("name_3_firstname")).clear();
        driver.findElement(By.id("name_3_firstname")).sendKeys(FIRST_NAME);
        driver.findElement(By.id("name_3_lastname")).clear();
        driver.findElement(By.id("name_3_lastname")).sendKeys(LAST_NAME);
        driver.findElement(By.xpath(MARITAL_STATUS)).click();
        driver.findElement(By.xpath(HOBBY)).click();
        new Select(driver.findElement(By.id("dropdown_7"))).selectByVisibleText(COUNTRY);
        new Select(driver.findElement(By.id("mm_date_8"))).selectByVisibleText(MONTH);
        new Select(driver.findElement(By.id("dd_date_8"))).selectByVisibleText(DAY);
        new Select(driver.findElement(By.id("yy_date_8"))).selectByVisibleText(YEAR);
        driver.findElement(By.id("phone_9")).clear();
        driver.findElement(By.id("phone_9")).sendKeys(PHONE_NUMBER);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(USER_NAME);
        driver.findElement(By.id("email_1")).clear();
        driver.findElement(By.id("email_1")).sendKeys(EMAIL);
        driver.findElement(By.id("profile_pic_10")).clear();
        driver.findElement(By.id("profile_pic_10")).sendKeys(PATH_TO_PROFILE_PICTURE);
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys(ABOUT_YOURSELF);
        driver.findElement(By.id("password_2")).clear();
        driver.findElement(By.id("password_2")).sendKeys(PASSWORD);
        driver.findElement(By.id("confirm_password_password_2")).clear();
        driver.findElement(By.id("confirm_password_password_2")).sendKeys(PASSWORD);
        driver.findElement(By.name("pie_submit")).click();
        //String result = driver.findElement(By.className("piereg_login_error")).getText();
        String result = driver.findElement(By.className("piereg_message")).getText();
        Assert.assertEquals(result, "Thank you for your registration");

    }

    // this test should only be run after previous test (testRegistrationPositive())
    @Test
    public void testRegistrationWithUsernameAlreadyRegistered() throws Exception {
        driver.get(baseUrl + "/registration/");
        driver.findElement(By.id("name_3_firstname")).clear();
        driver.findElement(By.id("name_3_firstname")).sendKeys(FIRST_NAME);
        driver.findElement(By.id("name_3_lastname")).clear();
        driver.findElement(By.id("name_3_lastname")).sendKeys(LAST_NAME);
        driver.findElement(By.xpath(MARITAL_STATUS)).click();
        driver.findElement(By.xpath(HOBBY)).click();
        new Select(driver.findElement(By.id("dropdown_7"))).selectByVisibleText(COUNTRY);
        new Select(driver.findElement(By.id("mm_date_8"))).selectByVisibleText(MONTH);
        new Select(driver.findElement(By.id("dd_date_8"))).selectByVisibleText(DAY);
        new Select(driver.findElement(By.id("yy_date_8"))).selectByVisibleText(YEAR);
        driver.findElement(By.id("phone_9")).clear();
        driver.findElement(By.id("phone_9")).sendKeys(PHONE_NUMBER);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(USER_NAME);
        driver.findElement(By.id("email_1")).clear();
        driver.findElement(By.id("email_1")).sendKeys(EMAIL);
        driver.findElement(By.id("profile_pic_10")).clear();
        driver.findElement(By.id("profile_pic_10")).sendKeys(PATH_TO_PROFILE_PICTURE);
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys(ABOUT_YOURSELF);
        driver.findElement(By.id("password_2")).clear();
        driver.findElement(By.id("password_2")).sendKeys(PASSWORD);
        driver.findElement(By.id("confirm_password_password_2")).clear();
        driver.findElement(By.id("confirm_password_password_2")).sendKeys(PASSWORD);
        driver.findElement(By.name("pie_submit")).click();
        String result = driver.findElement(By.className("piereg_login_error")).getText();
        Assert.assertEquals(result, "Error: Username already exists");

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
