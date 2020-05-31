package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TodoistAddProjectTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void main(String[] args) {
        navegarUrl("https://todoist.com");
        validarLandingPage();
        login("jomarnavarro@gmail.com", "Test@1234");
        validarHomePage();
        crearProyecto("proyectoXXXXL", "Teal");
        validarProyecto("proyectoXXXXL", "color: rgb(21, 143, 173);");
        terminarproceso();
    }

    private static void terminarproceso() {

        driver.quit();
    }

    private static void navegarUrl(String url) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    private static void validarLandingPage() {

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[mask*='#logo-wordmark']")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Inicia sesión")));

    }

    private static void login(String user, String password) {
        WebElement loginLink = driver.findElement(By.linkText("Inicia sesión"));
        loginLink.click();

        WebElement userLogin = driver.findElement(By.id("email"));
        userLogin.clear();
        userLogin.sendKeys(user);

        WebElement psdLogin = driver.findElement(By.id("password"));
        psdLogin.clear();
        psdLogin.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector(".submit_btn"));
        loginBtn.click();


    }

    private static void validarHomePage() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".left_control > button:last-of-type")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".quick_find__input")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#filter_upcoming")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-track='navigation|projects_panel']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-track='navigation|projects_quick_add']")));

    }

    private static void crearProyecto(String nombreProyecto, String color) {

        //Solución °1
        Actions action = new Actions(driver);
        WebElement plusSign = driver.findElement(By.cssSelector("[data-track='navigation|projects_quick_add']"));
        action.moveToElement(plusSign).build().perform();
        plusSign.click();

        //Solución °2
        //WebElement addBtn = driver.findElement(By.cssSelector("[data-track='navigation|projects_quick_add']"));
        //addBtn.click();

        WebElement nameProject = driver.findElement(By.id("edit_project_modal_field_name"));
        nameProject.sendKeys(nombreProyecto);

        WebElement colorProject = driver.findElement(By.cssSelector(".color_dropdown_toggle"));
        colorProject.click();

        List<WebElement> listColors = driver.findElements(By.cssSelector(".color_dropdown_select__name"));

        for(WebElement colors: listColors) {

            //WebElement nomColor = colors.findElement(By.cssSelector(".color_dropdown_select__name"));
            String textColor = colors.getText();

            if(textColor.equals(color)){
                colors.click();
                break;
            }
        }

        WebElement btnAdd = driver.findElement(By.cssSelector(".reactist_modal_box__actions > button:last-child"));
        btnAdd.submit();

    }


    private static void validarProyecto(String nombreProyecto, String color) {

        List<WebElement> listaProjects = driver.findElements(By.cssSelector(".clickable"));

        for(WebElement projects: listaProjects) {

            String nomProject = projects.getText();

            WebElement listaColores = projects.findElement(By.cssSelector("#projects_list > li > table > tbody > tr > td.td_color > div"));
            String listaColor = listaColores.getAttribute("Style");

            if(nomProject.equals(nombreProyecto) && listaColor.equals(color)){
                System.out.println("¡Proyecto encontrado!");
                break;
            }
        }
    }
}