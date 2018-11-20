import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainTest {
  public static void main(String[] args) {


//    Выбираем браузер для тестов, варианты: "chrome", "firefox","ie"
    WebDriver driver = DriverManager.getConfiguredDriver("chrome");


//    driver.manage().window().maximize();
//    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();

    driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

    WebElement loginEmail = driver.findElement(By.id("email"));
    loginEmail.sendKeys("webinar.test@gmail.com");

    WebElement loginPassword = driver.findElement(By.id("passwd"));
    loginPassword.sendKeys("Xcg7299bnSmMuRLp9ITw");

    WebElement submitButton = driver.findElement(By.name("submitLogin"));
    submitButton.click();


    WebElement navCatalog = driver.findElement(By.xpath("//body/div[@id='main']" +
            "/div[@class='bootstrap']/nav[@id='nav-sidebar']/ul[@class='menu']/li[@id='subtab-AdminCatalog']"));
//    WebElement navCatalog = driver.findElement(By.cssSelector("#subtab-AdminCatalog.maintab.has_submenu"));
//    actions.moveToElement(navCatalog).pause(Duration.ofSeconds(5)).build().perform();
//    WebElement navCatalog = driver.findElement(By.xpath("//*[@id='subtab-AdminCatalog']"));
//    WebElement navCategory = driver.findElement(By.cssSelector("#subtab-AdminCategories.active"));
//    navCategory.click();
    WebElement navCategory = driver.findElement(By.xpath("//body/div[@id='main']" +
            "/div[@class='bootstrap']/nav[@id='nav-sidebar']/ul[@class='menu']" +
            "/li[@id='subtab-AdminCatalog']/ul/li[@id='subtab-AdminCategories']/a"));


    Actions actions = new Actions(driver);
    actions.moveToElement(navCatalog).pause(Duration.ofSeconds(5)).click(navCategory).build().perform();

    WebElement catFrame = driver.findElement(By.cssSelector("div.panel.col-lg-12"));
//    String test = pageTitle.getText();
//    System.out.println(test);

    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.withMessage("Element is not visible")
            .until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector("div.panel.col-lg-12")));

    WebElement catAdd = driver.findElement(By.id("page-header-desc-category-new_category"));
    catAdd.click();

    wait.withMessage("Element is not visible")
            .until(ExpectedConditions
                    .visibilityOfElementLocated(By.id("name_1")));

    WebElement newcatName = driver.findElement(By.id("name_1"));
    WebElement submitNewcat = driver.findElement(By.id("category_form_submit_btn"));


    Date dateCat = new Date();
    long dateCatlong = dateCat.getTime();
    String catName = "TestCat_" + dateCatlong;

    newcatName.sendKeys(catName);
    submitNewcat.click();

    wait.withMessage("Category is not added")
            .until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector("div.alert.alert-success")));

////*[@id="content"]/div[@class='bootstrap']/div[@class='alert alert-success']


    WebElement filterName = driver.findElement(By.name("categoryFilter_name"));

    filterName.sendKeys(catName);

    WebElement filterSubmit = driver.findElement(By.id("submitFilterButtoncategory"));
    filterSubmit.click();

    wait.withMessage("Category is not find")
            .until(ExpectedConditions
                    .visibilityOfElementLocated(By.name("categoryBox[]")));


    WebElement dropdownImage = driver.findElement(By.id("employee_infos"));
    dropdownImage.click();

    WebElement logoutBtn = driver.findElement(By.id("header_logout"));
    logoutBtn.click();


    driver.quit();

//
  }

}

