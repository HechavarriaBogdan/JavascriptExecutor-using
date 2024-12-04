import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class Test1 {


    ChromeOptions options = new ChromeOptions();
    WebDriver driver = new ChromeDriver();
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @Test
    public void test1() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement input = driver.findElement(By.xpath(".//input[@name='my-date']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(input));
        input.click();
        WebElement element = driver.findElement(By.name("my-text"));
        element.click();
    }

    @AfterTest
    public void quit() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void test2() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        WebElement element1 = driver.findElement(By.xpath("//button[text()='Войти в аккаунт']"));
        String elementInfo = element1.getAttribute("nodeName");
        System.out.println(elementInfo);
        String elementText = element1.getText();
        assertEquals(elementText, "Войти в аккаунт");
        System.out.println(element1.getCssValue("font-family"));
        Actions action = new Actions(driver);
        WebElement moveFromElement = driver.findElement(By.xpath("//img[@src='https://code.s3.yandex.net/react/code/bun-02.png']"));
        WebElement movetoElement = driver.findElement(By.cssSelector(".constructor-element__image[alt='Перетяните булочку сюда (верх)']"));
        action.dragAndDrop(moveFromElement, movetoElement).build().perform();
    }

    @Test
    public void test3() {
        driver.get("https://pagination.js.org/");
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
        List<WebElement> pages = driver.findElements(By.xpath("//div[@class='paginationjs-pages']/ul/li"));
        String text = elements.get(5).getText();
        System.out.println(text);
        pages.get(2).click();
        wait.until(i -> ExpectedConditions.stalenessOf(elements.get(5)));
        List<WebElement> elements1 = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
        String text2 = elements1.get(5).getText();
        System.out.println(text2);
    }

    @Test
    // Открытие новой вкладки и переключение на нее
    public void test4() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.get("https://stellarburgers.nomoreparties.site/");
        String window1 = driver.getWindowHandle(); // посмотреть название (дескриптор) окна
        js.executeScript("window.open()"); // открываем новое окно
        Set<String> currentWindows = driver.getWindowHandles(); // записываем дескрипторы открытых окон в Set набор
        String window2 = null;
        for (String window: currentWindows) {
            if(!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        try {
            driver.switchTo().window(window2);
        } catch (NullPointerException e) {
            System.out.println("window2 can't be null");
        }
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.close();
        driver.switchTo().window(window1);
        WebElement element = driver.findElement(By.xpath("//button[text()='Войти в аккаунт']"));
        element.click();


    }


}
