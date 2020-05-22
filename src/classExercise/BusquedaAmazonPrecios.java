package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BusquedaAmazonPrecios {

    public static void main(String[] args) {

//        navegar a amazon
        WebDriver driver = new ChromeDriver();
        driver.get("http://amazon.com.mx/");

//        verificar que la pagina cargo correctamente
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        buscar un producto
        WebElement cajaBusqueda = driver.findElement(By.cssSelector("#twotabsearchtextbox"));


        WebElement lupa = driver.findElement(By.cssSelector("div.nav-search-submit .nav-input"));
        //WebElement lupa = driver.findElement(By.cssSelector("value=\"Ir\""));

        //System.out.println(By.class);
        cajaBusqueda.sendKeys("Laptop hp");
        lupa.click();

//      verificar que hay una lista de productos en la pagina
        WebElement verifica = driver.findElement(By.partialLinkText("HP"));

        //Obtener los precios de todas las laptops e imprimirlos en consola
        List<WebElement> listaPrecios = driver.findElements(By.cssSelector(".a-price-whole"));

        //Imprimir la lista de precios
        for (WebElement actual : listaPrecios) {
            //
            System.out.println(actual.getText());
        }
    }
}
