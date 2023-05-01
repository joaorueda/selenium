package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import org.h2.mvstore.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";


    public LoginPage() {
        super(null);
        browser.navigate().to(URL_LOGIN);
    }



    public static void preencheFormularioDeLogin(String usuario, String senha) {
        browser.findElement(By.id("username")).sendKeys(usuario);
        browser.findElement(By.id("password")).sendKeys(senha);
    }

    public static LeiloesPage efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public static boolean isPaginaDeLogin() {
        return browser.getCurrentUrl().equals("http://localhost:8080/login");
    }

    public static boolean isPaginaDeLoginComErro() {
        return browser.getCurrentUrl().equals("http://localhost:8080/login?error");
    }

    public static String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static void navegaParaPaginaDeLances() {
        browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public static boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto);
    }
}
