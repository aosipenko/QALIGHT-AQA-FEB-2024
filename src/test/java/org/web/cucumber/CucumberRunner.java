package org.web.cucumber;


import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.web.driver.WebDriverFactory;
import org.web.page.AlloPage;
import org.web.page.FileBinPage;
import org.web.page.GooglePage;
import org.web.util.DataHolder;

@EnableTransactionManagement
@EnableJpaRepositories("org.web")
@CucumberContextConfiguration
@ContextConfiguration(locations = "classpath*:spring/spring-context.xml")
@CucumberOptions(
//        tags = "@my-new-test",
        features = "src/test/resources/features",
        glue = "org.web.cucumber",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    protected WebDriver driver;

    @AfterMethod
    public void resetHolder() {
        DataHolder.reset();
    }

    @BeforeSuite
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        MySteps.googlePage = new GooglePage(driver);
        MySteps.alloUaPage = new AlloPage(driver);
        FileSteps.fileBinPage = new FileBinPage(driver);
        WebSteps.driver = driver;
    }

    @SneakyThrows
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
