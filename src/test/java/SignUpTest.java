import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUpTest {
    WebDriver browser;

    @BeforeTest
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    @AfterTest
    public void closeBrowser() {
        browser.quit();
    }

    @Test
    public void zipCodeShouldAccept5Digits(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("11111");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test
    public void zipCodeEmptyField(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void zipCodeShouldNotAccept4Digits(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("1111");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void zipCodeShouldNotAccept6Digits(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("111111");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void zipCodeShouldNotAcceptSymbols(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("!£$%^&");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void zipCodeShouldNotAcceptLetters(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("aaaaa");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void successRegistration(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        WebElement firstNameInput = browser.findElement(By.name("first_name"));
        firstNameInput.sendKeys("TestFirstName");
        WebElement lastNameInput = browser.findElement(By.name("last_name"));
        lastNameInput.sendKeys("TestLastName");
        WebElement emailInput = browser.findElement(By.name("email"));
        emailInput.sendKeys("test@test.test");
        WebElement passwordInput = browser.findElement(By.name("password1"));
        passwordInput.sendKeys("123123123");
        WebElement confirmPasswordInput = browser.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123123123");
        WebElement registerButton = browser.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        Assert.assertEquals(browser.findElement(By.className("confirmation_message")).getText(), "Account is created!");
    }

    @Test
    public void registrationWithoutFirstName(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        WebElement firstNameInput = browser.findElement(By.name("first_name"));
        firstNameInput.sendKeys("");
        WebElement lastNameInput = browser.findElement(By.name("last_name"));
        lastNameInput.sendKeys("TestLastName");
        WebElement emailInput = browser.findElement(By.name("email"));
        emailInput.sendKeys("test@test.test");
        WebElement passwordInput = browser.findElement(By.name("password1"));
        passwordInput.sendKeys("123123123");
        WebElement confirmPasswordInput = browser.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123123123");
        WebElement registerButton = browser.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    @Test
    public void registrationWithInvalidFirstName(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        WebElement firstNameInput = browser.findElement(By.name("first_name"));
        firstNameInput.sendKeys("---");
        WebElement lastNameInput = browser.findElement(By.name("last_name"));
        lastNameInput.sendKeys("TestLastName");
        WebElement emailInput = browser.findElement(By.name("email"));
        emailInput.sendKeys("test@test.test");
        WebElement passwordInput = browser.findElement(By.name("password1"));
        passwordInput.sendKeys("123123123");
        WebElement confirmPasswordInput = browser.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123123123");
        WebElement registerButton = browser.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    @Test
    public void registrationWithOutLastName(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        WebElement firstNameInput = browser.findElement(By.name("first_name"));
        firstNameInput.sendKeys("TestfirstName");
        WebElement lastNameInput = browser.findElement(By.name("last_name"));
        lastNameInput.sendKeys("");
        WebElement emailInput = browser.findElement(By.name("email"));
        emailInput.sendKeys("test@test.test");
        WebElement passwordInput = browser.findElement(By.name("password1"));
        passwordInput.sendKeys("123123123");
        WebElement confirmPasswordInput = browser.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123123123");
        WebElement registerButton = browser.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        Assert.assertEquals(browser.findElement(By.className("confirmation_message")).getText(), "Account is created!");
    }

    @Test
    public void registrationWithOutEmail(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        WebElement firstNameInput = browser.findElement(By.name("first_name"));
        firstNameInput.sendKeys("TestfirstName");
        WebElement lastNameInput = browser.findElement(By.name("last_name"));
        lastNameInput.sendKeys("");
        WebElement emailInput = browser.findElement(By.name("email"));
        emailInput.sendKeys("");
        WebElement passwordInput = browser.findElement(By.name("password1"));
        passwordInput.sendKeys("123123123");
        WebElement confirmPasswordInput = browser.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123123123");
        WebElement registerButton = browser.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
    @Test
    public void registrationWithInvalidEmail(){
        WebElement zipCodeInput = browser.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = browser.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        WebElement firstNameInput = browser.findElement(By.name("first_name"));
        firstNameInput.sendKeys("TestfirstName");
        WebElement lastNameInput = browser.findElement(By.name("last_name"));
        lastNameInput.sendKeys("");
        WebElement emailInput = browser.findElement(By.name("email"));
        emailInput.sendKeys("dsdsdsa");
        WebElement passwordInput = browser.findElement(By.name("password1"));
        passwordInput.sendKeys("123123123");
        WebElement confirmPasswordInput = browser.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123123123");
        WebElement registerButton = browser.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();
        Assert.assertEquals(browser.findElement(By.className("error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }
}
