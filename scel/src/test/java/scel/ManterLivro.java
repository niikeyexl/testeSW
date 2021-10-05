package scel;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManterLivro {
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void cadastrarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("4621");
		driver.findElement(By.id("autor")).sendKeys("Irineu");
		driver.findElement(By.id("titulo")).sendKeys("Você não sabe nem eu");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		assertTrue(driver.findElements(By.xpath("//tr[contains(.,\'4621\')]")).size() == 1);
		
		driver.findElement(By.xpath("//tr[contains(.,\'4621\')]/td[5]/div/a[2]")).click();
	}

	@Test
	public void editarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("8721");
		driver.findElement(By.id("autor")).sendKeys("O grande");
		driver.findElement(By.id("titulo")).sendKeys("pequeno homem");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("a[href=\"/sig/livros/8721\"]")).click();
		driver.findElement(By.id("autor")).clear();
		driver.findElement(By.id("autor")).sendKeys("O pequeno");
		driver.findElement(By.id("titulo")).clear();
		driver.findElement(By.id("titulo")).sendKeys("Grande homem");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		assertTrue(driver.findElement(By.xpath("//tr[contains(.,\'8721\')]/td[3]")).getText().equals("Grande homem"));
		assertTrue(driver.findElement(By.xpath("//tr[contains(.,\'8721\')]/td[4]")).getText().equals("O pequeno"));

		driver.findElement(By.xpath("//tr[contains(.,\'8721\')]/td[5]/div/a[2]")).click();
	}

	@Test
	public void buscarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("9826");
		driver.findElement(By.id("autor")).sendKeys("Piton");
		driver.findElement(By.id("titulo")).sendKeys("Tudo na vida depende");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		assertTrue(driver.findElements(By.xpath("//tr[contains(.,\'9826\')]")).size() == 1);
		
		driver.findElement(By.xpath("//tr[contains(.,\'9826\')]/td[5]/div/a[2]")).click();
	}

	@Test
	public void deletarLivro() {
		driver.findElement(By.linkText("Livros")).click();
		driver.findElement(By.id("isbn")).sendKeys("9910");
		driver.findElement(By.id("autor")).sendKeys("Um grande mentiroso");
		driver.findElement(By.id("titulo")).sendKeys("Palmeiras tem mundial");
		driver.findElement(By.id("titulo")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//tr[contains(.,\'9910\')]/td[5]/div/a[2]")).click();
		assertTrue(driver.findElements(By.xpath("//tr[contains(.,\'9910\')]")).size() == 0);
	}
}