


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class NewProductTest {

    private WebDriver webDriver;
    private String url;
    private String createProductUrl;
    private String productsListUrl;
    private String login;
    private String password;
    private String productSymbol;
    private String description;
    private String price;
    private String weight;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        webDriver = new FirefoxDriver(options);
        url = "https://localhost:8181/faces/common/signIn.xhtml";
        createProductUrl = "https://localhost:8181/faces/product/createNewProduct.xhtml";
        productsListUrl = "https://localhost:8181/faces/product/listProducts.xhtml";
        login = "LRey";
        password = "P@ssw0rd";
        productSymbol = "1234567890000";
        description = "Test product";
        price = "56.78";
        weight = "222";
    }

    @Test
    public void newProductTest() throws Exception {

        webDriver.get(url);

        // sign in to office account
        WebElement loginElem = webDriver.findElement(By.name("j_username"));
        WebElement passwordElem = webDriver.findElement(By.name("j_password"));
        loginElem.clear();
        passwordElem.clear();
        loginElem.sendKeys(login);
        passwordElem.sendKeys(password);
        webDriver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input")).click();

        webDriver.get(createProductUrl);

        // check if user signed in 
        Assert.assertTrue(webDriver.getPageSource().contains("Authenticated user: LRey")
                || webDriver.getPageSource().contains("Uwierzytelniony użytkownik: LRey"));

        // create new product
        WebElement productSymbolElem = webDriver.findElement(By.name("CreateProductForm:productSymbol"));
        productSymbolElem.clear();
        WebElement descriptionElem = webDriver.findElement(By.name("CreateProductForm:description"));
        descriptionElem.clear();
        WebElement priceElem = webDriver.findElement(By.name("CreateProductForm:price"));
        priceElem.clear();
        WebElement weightElem = webDriver.findElement(By.name("CreateProductForm:weight"));
        weightElem.clear();
        productSymbolElem.sendKeys(productSymbol);
        descriptionElem.sendKeys(description);
        priceElem.sendKeys(price);
        weightElem.sendKeys(weight);
        webDriver.findElement(By.name("CreateProductForm:j_idt35")).click();

        // go to list of products
        webDriver.get(productsListUrl);

        // check if new product created
        Assert.assertTrue(webDriver.getPageSource().contains("1234567890000"));

        // delete previously created product
        webDriver.findElement(By.name("j_idt26:j_idt27:0:onlyOffice:j_idt40")).click();
        webDriver.findElement(By.name("DeleteProductForm:j_idt30")).click();

        // check if product deleted
        Assert.assertFalse(webDriver.getPageSource().contains("1234567890000"));
    }

    @AfterClass
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
