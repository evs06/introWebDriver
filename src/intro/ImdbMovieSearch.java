package intro;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ImdbMovieSearch extends ParentTest{

	public static void main (String[] args) {

//		String a="a";
//		String b="b";
//		String c="c";
		setUp();
		testMovieSearch("It", "Richard Thomas", "1990");
		tearDown();		
	}
}

