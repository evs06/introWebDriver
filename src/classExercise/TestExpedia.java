package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.security.Key;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class TestExpedia {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {
        navegarUrl("https://www.expedia.mx");
        verificarHomePageExpedia();
        buscarVuelvo("Guadalajara", "Toronto",
                     "25/01/2021","31/01/2021", 1);
        verificarListaDeVuelos();
        seleccionarVuelo();
        verificarSeleccionVuelo();
        terminarproceso();

    }

    private static void verificarSeleccionVuelo() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".margin-less")));

    }

    private static void terminarproceso() {

        driver.quit();

    }

//

    private static void seleccionarVuelo() {

        //List<WebElement> listaVuelos = driver.findElements(By.cssSelector("[data-test-id=listing-main]"));
        //  List<WebElement> listaVuelos = driver.findElements(By.cssSelector("[data-test-id=listing-main]"));
        List<WebElement> listaVuelos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-test-id=listing-main]")));
        WebElement vueloSeleccion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"flight-module-2021-01-25t06:00:00-06:00-coach-gdl-iah-ua-6047-coach-iah-yyz-ua-6062_2021-01-31t08:00:00-05:00-coach-yyz-iah-ua-6315-coach-iah-gdl-ua-332_\"]/div[1]/div[2]/div[2]/div/div[2]/button")));
        System.out.println("Son " + listaVuelos.size() + " vuelos para tu destino.");

        vueloSeleccion.click();

    }

    private static void verificarListaDeVuelos() {


        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".title-city-text")));

    }

    private static void buscarVuelvo(String origen, String destino, String fechaSalida, String fechaRegreso, int personaExtra) {


        WebElement cajaOrigen = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#flight-origin-hp-flight")));
        WebElement cajaDestino = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#flight-destination-hp-flight")));
        WebElement cajaFechaSalida = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#flight-departing-hp-flight")));
        WebElement cajaFechaDestino = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#flight-returning-hp-flight")));
        WebElement comboPersona = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#traveler-selector-hp-flight > div > ul > li > button")));
        WebElement botonBusqueda = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='gcw-flights-form-hp-flight']/div[9]/label/button")));

        //Limpiar e ingresar Origen
        cajaOrigen.clear();
        cajaOrigen.sendKeys(origen);
        WebElement listaOrigen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aria-option-0")));
        listaOrigen.click();

        //Limpiar e ingresar Destino
        cajaDestino.clear();
        cajaDestino.sendKeys(destino);
        WebElement listaDestino = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aria-option-0")));
        listaDestino.click();

        //Limpiar e ingresar Salida
        cajaFechaSalida.clear();
        cajaFechaSalida.sendKeys(fechaSalida);

        //Limpiar e ingresar Regreso, solución 1
        cajaFechaDestino.click();

        for(int x=0; x<9; x++){
            cajaFechaDestino.sendKeys(Keys.BACK_SPACE);
        }

        cajaFechaDestino.sendKeys(Keys.DELETE);
        cajaFechaDestino.sendKeys(fechaRegreso);

        //Limpiar e ingresar Regreso, solución 2
        //cajaFechaDestino.click();
        //WebElement dateRegreso = driver.findElement(By.cssSelector("#flight-returning-wrapper-hp-flight > div > div > div:nth-child(4) > table > tbody > tr:nth-child(5) > td:nth-child(7) > button"));
        //dateRegreso.click();

        //Limpiar e ingresar Regreso, solución 3
        //-Primero Fecha Regreso
        //cajaFechaDestino.clear();
        //cajaFechaDestino.sendKeys(fechaRegreso);

        //-Segundo Fecha Salida
        //cajaFechaSalida.clear();
        //cajaFechaSalida.sendKeys(fechaSalida);


        //Selección de personas
        comboPersona.click();
        //WebElement personaExt = driver.findElement(By.cssSelector("#traveler-selector-hp-flight > " +
                //"div > ul > li > div > div > div > div.uitk-grid.step-input-outside.gcw-component.gcw-component-step-input.gcw-step-input.gcw-component-initialized " +
                //"> div:nth-child(4) > button > span.uitk-icon > svg"));
        WebElement personaExt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='traveler-selector-hp-flight']/div/ul/li/div/div/div/div[1]/div[4]/button")));

        for(int x=0; x<personaExtra; x++){
            personaExt.click();
        }

        //Click en el botón Buscar vuelo
        botonBusqueda.click();
    }

    private static void verificarHomePageExpedia() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[alt='www.expedia.mx']")));
        //driver.findElement(By.cssSelector("[alt='www.expedia.mx']"));
        WebElement buttonFlight = wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-flight-tab-hp")));
        //WebElement buttonFlight = driver.findElement(By.id("tab-flight-tab-hp"));

        buttonFlight.click();
    }

    private static void navegarUrl(String url) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}