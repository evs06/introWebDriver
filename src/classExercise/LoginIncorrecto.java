package classExercise;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginIncorrecto {
    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {

        navegarUrl("https://evening-bastion-49392.herokuapp.com/");
        verificarHomePage();
        loginIncorrecto("frenk", "sinotra");
        verificarMensajeError("The username or password you entered are incorrect");
        terminaproceso();

    }

    private static void terminaproceso() {

        driver.quit();
    }

    private static void navegarUrl(String url) {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        driver.get(url);

    }

    private static void verificarHomePage() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("log in")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[alt='Frank Sinatra']")));


    }

    private static void loginIncorrecto(String badUser, String badPass) {
        WebElement logIn = driver.findElement(By.cssSelector("[href='/login']"));
        logIn.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[value='Log In']")));

        WebElement userName = driver.findElement(By.cssSelector("#username"));
        WebElement passName = driver.findElement(By.cssSelector("#password"));
        WebElement buttonLogin = driver.findElement(By.cssSelector("[value='Log In']"));

        userName.sendKeys(badUser);
        passName.sendKeys(badPass);

        buttonLogin.click();

    }

    private static void verificarMensajeError(String errorMessage) {

        WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.notice")));
        System.out.println(mensajeError.getText());

    }
}