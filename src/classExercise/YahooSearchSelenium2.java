package classExercise;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YahooSearchSelenium2 {
	static WebDriver driver;

	public static void main(String[] args) {
		//que hace tu caso de prueba.
		//configura el navegador
		configurarNavegador("chrome");
		//busca una palabra clave
		buscarPalabraClave("selenium");
		//darle click al link apropiado
		darClickAlLink("Selenium");
		//cambiar de pagina
		cambiarDePagina();
		//darle click al link de descarga
		darClickAlLink("Downloads");
		//Destruye lo abierto
		destruirConfiguracion();
	}

	private static void cambiarDePagina() {
		ArrayList<String> windowIds = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Number of windows: " + windowIds.size());
		
		for(String windowId: driver.getWindowHandles()) {
			driver.switchTo().window(windowId);
		}
		
	}

	private static void darClickAlLink(String linkText) {
		WebElement seleniumLink = driver.findElement(By.linkText(linkText));
		seleniumLink.click();
		
	}

	private static void buscarPalabraClave(String string) {
		WebElement searchBox = driver.findElement(By.cssSelector("[role=\"combobox\"]"));
		WebElement searchButton = driver.findElement(By.className("D(tbc)"));
		
		searchBox.clear();
		searchBox.sendKeys("Selenium");
		searchButton.click();
		
	}

	private static void configurarNavegador(String string) {
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,  TimeUnit.SECONDS);
		driver.get("http://www.yahoo.com");
		
	}

	private static void destruirConfiguracion() {
		driver.close();

	}

}
