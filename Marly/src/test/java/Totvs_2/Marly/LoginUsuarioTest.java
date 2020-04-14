package Totvs_2.Marly;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class LoginUsuarioTest {
    private WebDriver navegador;
    @Before
    public void setUp() {
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Navegando para o link da aplicação
        navegador.get("http://marly.brazilsouth.azurecontainer.io");
    }
        @Test
        public void testUsuarioInvalido() {

            //Digitar no campo de nome "username" o texto "Marly"
            WebElement Usuario = navegador.findElement(By.xpath("//input[@formcontrolname=\"username\"]\n"));
            Usuario.findElement(By.xpath("//input[@formcontrolname=\"username\"]\n")).sendKeys("Marly");

            //Digitar no campo de nome "password" a senha "110607"
            WebElement Senha = navegador.findElement(By.xpath("//input[@formcontrolname=\"password\"]"));
            Senha.findElement(By.xpath("//input[@formcontrolname=\"password\"]")).sendKeys("123");

            //Clicar no comando de nome "Conecte-se"
            navegador.findElement(By.xpath("//button[@type=\"submit\"]")).click();

            //Capiturando o elemento que leva até  mensagem de senha inválida
            WebElement alertMensagem = navegador.findElement(By.xpath(" //div[@role=\"alert\"]"));
            String textoalertMensagem = alertMensagem.getText();

            //Validação da mensagem de senha/login inválidos
            assertEquals("Login/Password invalid. Try again.", textoalertMensagem);

        }

        @Test
        public void testUsuarioParticiparDoLeilao() {

            //Digitar no campo de nome "username" o texto "Marly"
            WebElement Usuario = navegador.findElement(By.xpath("//input[@formcontrolname=\"username\"]\n"));
            Usuario.findElement(By.xpath("//input[@formcontrolname=\"username\"]\n")).sendKeys("Teste2");

            //Digitar no campo de nome "password" a senha "110607"
            WebElement Senha = navegador.findElement(By.xpath("//input[@formcontrolname=\"password\"]"));
            Senha.findElement(By.xpath("//input[@formcontrolname=\"password\"]")).sendKeys("12345");

            //Clicar no comando de nome "Conecte-se"
            navegador.findElement(By.xpath("//button[@type=\"submit\"]")).click();

            //Validar que exibe  a listagem de leilões
            WebElement pagina = navegador.findElement(By.xpath("//h3[text()=\"Auctions\"]"));
            String textoNoElementoPagina = pagina.getText();

            //Validação
            assertEquals("Auctions", textoNoElementoPagina);

            //Clicar no botão Create Auction
            navegador.findElement(By.xpath("//a[@routerlink=\"/main/newauction\"]")).click();

            //Preencher o campo Nome do leião
            WebElement novoLeilao = navegador.findElement(By.xpath("//input[@id=\"name\"]"));
            novoLeilao.findElement(By.xpath("//input[@id=\"name\"]")).sendKeys("Testando auto 4");

            //Preencher o campo Valor
            WebElement valorLeilao = navegador.findElement(By.xpath("//input[@id=\"value\"]"));
            valorLeilao.findElement(By.xpath("//input[@id=\"value\"]")).sendKeys("30");

            //Marcar checkbok
            navegador.findElement(By.xpath("//label[@for=\"used\"]")).click();
            navegador.findElement(By.xpath("//button[@type=\"submit\"]")).click();

            //Clicar em Leilões
            navegador.findElement(By.xpath("//a[text()=\"Auctions\"]")).click();

            //Clicar no botão "Sing Out" ou "Sair" (Não apagar)
            navegador.findElement(By.xpath("//a[text()=\"Sign Out\"] ")).click();

        }

        @Test
        public void testUsuarioNaoAdministrador() {

            //Digitar no campo de nome "username" o texto "Marly"
            WebElement Usuario = navegador.findElement(By.xpath("//input[@formcontrolname=\"username\"]\n"));
            Usuario.findElement(By.xpath("//input[@formcontrolname=\"username\"]\n")).sendKeys("Naoadm");

            //Digitar no campo de nome "password" a senha "110607"
            WebElement Senha = navegador.findElement(By.xpath("//input[@formcontrolname=\"password\"]"));
            Senha.findElement(By.xpath("//input[@formcontrolname=\"password\"]")).sendKeys("12345");

            //Clicar no comando de nome "Conecte-se"
            navegador.findElement(By.xpath("//button[@type=\"submit\"]")).click();

            //navegador.findElement(By.xpath("//a[@routerlink=\"/main/newauction\"]")).click();

            WebElement barraMenus = navegador.findElement(By.xpath("//span[text()=\"(current)\"]"));
            String textobarraMenus = barraMenus.getText();

            assertEquals("(current)", textobarraMenus);

            //Sair da aplicação
            navegador.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();

        }

        @After
        public void tearDown() {
            //Fechar o navegador
            navegador.quit();
            //navegador.close();

        }

    }

