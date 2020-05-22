package classExercise;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FacebookAddFriend {


	static ChromeOptions opt;
	static WebDriver driver;

	public static void main(String[] args) { 

		//configurarNavegador



		configurarNavegador();

		//login 

		logearFacebook("", "");

		//buscar 

		buscarAmigo("Sergio Perez Perez");

		//agregar 

		agregarAmigo("nombreAmigo");
		
		destruirConfiguracion();

	} 


	private static void destruirConfiguracion() {
		//driver.close();
		
	}


	private static void configurarNavegador() {

		opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");

		driver = new ChromeDriver(opt);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://www.facebook.com"); 

	} 

	private static void logearFacebook(String usuario, String password) {
		WebElement campoUsuario; //id=email
		WebElement campoPassword; //id="pass"
		WebElement botonLogin; //data-testid="royal_login_button"

		campoUsuario = driver.findElement(By.id("email"));
		campoPassword = driver.findElement(By.id("pass"));
		botonLogin = driver.findElement(By.xpath("//input[@data-testid='royal_login_button']"));
		//botonLogin = driver.findElement(By.xpath("//*[@id=\"u_0_b\"]"));

		campoUsuario.clear();
		campoUsuario.sendKeys(usuario);
		campoPassword.clear();
		campoPassword.sendKeys(password);
		botonLogin.click();
	}

	private static void agregarAmigo(String string) {
		

	}

	private static void buscarAmigo(String nombreAmigo) {

		WebElement buscador; //
		WebElement botonBuscar; //id="pass"
		WebElement botonLogin; //data-testid="royal_login_button"

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		buscador = driver.findElement(By.cssSelector("[name=\"q\"]"));
		botonBuscar = driver.findElement(By.className("_585_"));
		//campoPassword = driver.findElement(By.id("pass"));
		//botonLogin = driver.findElement(By.xpath("//input[@data-testid='royal_login_button']"));
		//botonLogin = driver.findElement(By.xpath("//*[@id=\"u_0_b\"]"));

		//driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		//driver.wait(3000,10);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		buscador.sendKeys(nombreAmigo);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		botonBuscar.click();

		//campoPassword.clear();
		//campoPassword.sendKeys(password);
		//botonLogin.click();

	}


}
