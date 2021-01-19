import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EditLocationTest {
    
    WebDriver driver;
    WebDriverWait waitDriver;
    String signIn;
    String username;
    String password;
    String newLocation;
    String locationList;
    String location;
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        waitDriver = new WebDriverWait(driver, 10);
        signIn = "https://localhost:8181/faces/common/signIn.xhtml";
        locationList = "https://localhost:8181/faces/location/listLocations.xhtml";
        username = "JDoe";
        password = "P@ssw0rd";
    }
    
    @Test
    public void editLocationTest() throws InterruptedException{
        //sign in as warehouse
        driver.get(signIn);
        
        WebElement login = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("j_username")));
        login.clear();
        login.sendKeys(username);
        
        WebElement pass = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("j_password")));
        pass.clear();
        pass.sendKeys(password);

        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".content > form:nth-child(1) > p:nth-child(2) > input:nth-child(2)"))).click();
                       
        //display list
        driver.get(locationList);
        
        //gut current location type
        WebElement locationType = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)")));
        String oldType = locationType.getText();

        //go to edition
        WebElement editFirstLocationBtn = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(5) > input:nth-child(1)")));
        editFirstLocationBtn.click();
        
        //pick a new type
        WebElement newTypePicker = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("EditLocationForm:locationType")));
        newTypePicker.click();
        
        //get two different types of location
        WebElement newType1 = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#EditLocationForm\\:locationType > option:nth-child(4)")));
        WebElement newType2 = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#EditLocationForm\\:locationType > option:nth-child(3)")));
        String type1 = newType1.getText();
        String type2 = newType2.getText();

        //assign a new type
        if(!oldType.equals(type1)){
            newType1.click();
            waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("EditLocationForm:j_idt31"))).click();
            WebElement editedType = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)")));
            Assert.assertFalse(editedType.getText().equals(oldType));
            Assert.assertEquals(editedType.getText(), type1);
        }
        else{
            newType2.click();
            waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("EditLocationForm:j_idt31"))).click();
            WebElement editedType = waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)")));  
            Assert.assertFalse(editedType.getText().equals(oldType));
            Assert.assertEquals(editedType.getText(), type2);
        }  
    }
    
    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }     
    }
    
}
