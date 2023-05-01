package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {

    private static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";


    public LeiloesPage(WebDriver browser) {
        super(browser);
        LeiloesPage.browser = browser;
    }


    public CadastroLeilaoPage carregaFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILOES);
        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String hoje) {
        WebElement ultimaLinha = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = ultimaLinha.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaData = ultimaLinha.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValor = ultimaLinha.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) &&
                colunaData.getText().equals(hoje) &&
                colunaValor.getText().equals(valor);
    }

    public boolean isPaginaAtual() {
        String currentUrl = browser.getCurrentUrl();
        return currentUrl.equals(URL_LEILOES);
    }
}
