package intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YahooSearchMain {


	public static void main(String[] args) {
		//INICIALIZACION DE SYSTEM.SETPROPERTY()
		//Se declara el tipo de variable como WebDriver
	    WebDriver driver;
	    //Se instancia la variable como ChromeDriver
		driver = new ChromeDriver();
		//1.- Toda la linea significa que se establece un tiempo de 60 segundos como el tiempo de espera predeterminado
		//2.- Si no se realiza la acción en el tiempo establecido se mostraría un mensaje de error
		//3.- implicitlyWait acepta el primer parametro como un número entero
		//4.- implicitlyWait acepta el segundo parametro como segundos, mili, micro, nano, dias, horas, etc
		driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
		//Se ingresa en el browser la URL de Yahoo
		driver.get("http://www.yahoo.com");
		//Se identifica por medio de "ID" el elemento de la barra de búsqueda
		WebElement searchBox = driver.findElement(By.id("header-search-input"));
		//Se identifica por medio de "ID" el elemento del botón Buscar
		WebElement searchButton = driver.findElement(By.id("header-desktop-search-button"));

		//El elemento de búsqueda es limpiado
		searchBox.clear();
		//Se ingresa en el campo Búsqueda la palabra Selnium para que sea buscada
		searchBox.sendKeys("Selenium");
		//Se hace clic en el botón de Búsqueda (icono de lupa)
		searchButton.click();

		//Se identifica por medio de "LinkText(nombre del link)" el elemento de lista de resultados
		WebElement seleniumLink = driver.findElement(By.linkText("Selenium"));
		//Se hace clic en el Link encontrado
		seleniumLink.click();

		//1.- Se crea un arreglo de lista
		//2.- Se declara la variable como ArrayList de tipo String
		//3.- Se instancia la variable windowsIds como ArrayList la variable y el método getWindowHandles
		//4.- getWindowHandles se usa para manejar múltiples ventanas y retorna un conjunto String
		//5.- windowIds va a contener las 2 ventanas que están abiertas
		ArrayList<String> windowIds = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowIds.get(0));
		driver.switchTo().window(windowIds.get(1));
		driver.switchTo().window(windowIds.get(0));


		//Se va a imprimir el número de ventanas abiertas, que en este caso son 2
		System.out.println("Number of windows: " + windowIds.size());

		//Cambia el control de la ventana que se tenga dependiendo en qué ventana se encuentre
		//String [] windowIds = driver.getWindowHandles().toArray();
		//Se recorre toda la lista de arreglo
	//	for(String windowId: driver.getWindowHandles()) {
	//		//Cambia el control de la ventana que se tenga dependiendo en qué ventana se encuentre
	//		driver.switchTo().window(windowId);
	//	}


		//Se identifica por medio de "LinkText(nombre del link)" el elemento de los menú de arriba de la pantalla
		WebElement downloadLink = driver.findElement(By.linkText("Downloads"));
		//Se hace clic en el Link encontrado
		downloadLink.click();

		//Es cerrada la ventana actual
		//Aparte driver tiene actualmente el valor de la segunda ventana
		driver.close();

	//	driver.SwitchTo().Window(driver.WindowHandles[0]);

		//Cambia el controlador a la ventana o pestaña original
		//driver.switchTo().window(originalWindow);

	}

}
