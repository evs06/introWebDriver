package intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class TestAmazonSeleccionarMejorProducto {

    static WebDriver driver;
    static By idcajaBusqueda = By.id("twotabsearchtextbox");
    static By idlupa = By.id("nav-search-submit-text");


    public static void main(String[] args) {
        navegarUrl("https://amazon.com.mx");
        verificarHomePageAmazon();
        buscarProducto("HP Laptop");
        verificarListaProductos("HP");
        Articulo mejor = seleccionarMejorArticulo();
     //   verificarArticuloEnCarrito(mejor);
        terminarproceso();

    }

    private static void terminarproceso() {

        driver.quit();

    }

    private static void verificarArticuloEnCarrito(Articulo mejor) {
    }

    private static Articulo seleccionarMejorArticulo() {
        Articulo mejorArticulo = null;
        List<WebElement> listArticulos = driver.findElements(By.cssSelector("[data-component-type='s-search-result']"));
        System.out.println(listArticulos.size());

        for(WebElement art: listArticulos) {
            try {
            //sacar el nombre
            WebElement nombreArticuloLink = art.findElement(By.cssSelector("h2 a"));
            String nombreArticulo = nombreArticuloLink.getText();

            //sacar el precio
            WebElement precioArticuloTxt = art.findElement(By.cssSelector(".a-price-whole"));
            int precioArticulo = Integer.parseInt(precioArticuloTxt.getText().replace(",", ""));
            //sacar la calificacion


                WebElement califArticuloTxt = art.findElement(By.cssSelector("i.a-icon-star-small"));
                String todaCalificacion = califArticuloTxt.getAttribute("textContent");
                double califArticulo = Double.parseDouble(todaCalificacion.split(" ")[0]);

                System.out.println("Nombre Artículo: " + nombreArticulo);
                System.out.println("Precio Artículo: " + precioArticulo);
                System.out.println("Calificación Artículo: " + califArticulo);
                System.out.println("");
                if(mejorArticulo == null) {
                    mejorArticulo = new Articulo(nombreArticulo, precioArticulo, califArticulo);
                } else {
                    Articulo actual = new Articulo(nombreArticulo, precioArticulo, califArticulo);
                    if(actual.esMejor(mejorArticulo)) {
                        mejorArticulo = actual;
                    }
                }

            }
            catch (org.openqa.selenium.NoSuchElementException e){

            }


            //saco un articulo.  Si es mejor que el anterior, mejorArticulo es el articulo actual.
            // si el articulo tiene nombre, precio y calificacion, meterlo a la lista de articulos.
        }
        WebElement nomArticulolnk = driver.findElement(By.linkText(mejorArticulo.nombre));
        nomArticulolnk.click();
        // voy a sacar el articulo con mejor calificacion.
        return mejorArticulo;
    }

    private static void verificarListaProductos(String hp) {
    }

    private static void buscarProducto(String hp_laptop) {

        WebElement cajaBusqueda =driver.findElement(idcajaBusqueda);
        WebElement lupa = driver.findElement(idlupa);

        //cajaBusqueda.clear();
        cajaBusqueda.sendKeys(hp_laptop);
        lupa.submit();

    }

    private static void verificarHomePageAmazon() {

        driver.findElement(idcajaBusqueda);
        //driver.findElement(By.cssSelector("[value='Ir']"));
        driver.findElement(idlupa);


    }

    private static void navegarUrl(String url) {

        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
}