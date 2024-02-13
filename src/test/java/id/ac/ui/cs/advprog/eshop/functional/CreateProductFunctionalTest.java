package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution
     * Set automatically during each test run by Spring Framework's test context
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product", testBaseUrl, serverPort);
    }

    @Test
    void createButton_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/list");
        WebElement createButton = driver.findElement(By.linkText("Create Product"));
        createButton.click();

        String expectedUrl = baseUrl + "/create";
        String pageUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, pageUrl);
    }

    @Test
    void pageTitle_create_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/create");

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createForm_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/create");

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        String productName = "Sampo Cap Gojo";
        productNameInput.sendKeys(productName);

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        String productQuantity = "5";
        productQuantityInput.sendKeys(productQuantity);

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        String expectedUrl = baseUrl + "/list";
        String pageUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, pageUrl);

        WebElement productList = driver.findElement(By.tagName("body"));
        assertTrue(productList.getText().contains("Sampo Cap Gojo"));
        assertTrue(productList.getText().contains("5"));
    }


}
