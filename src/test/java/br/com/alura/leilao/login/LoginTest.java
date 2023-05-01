package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginTest {


    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach(){
        loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        loginPage.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos (){
        loginPage.preencheFormularioDeLogin("fulano","pass");
        loginPage.efetuaLogin();

        Assert.assertFalse(loginPage.isPaginaDeLogin());
        Assert.assertEquals("fulano", loginPage.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        loginPage.preencheFormularioDeLogin("invalido","123");
        loginPage.efetuaLogin();

        Assert.assertTrue(loginPage.isPaginaDeLoginComErro());
        Assert.assertNull(loginPage.getNomeUsuarioLogado());
        Assert.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        loginPage.navegaParaPaginaDeLances();
        Assert.assertTrue(loginPage.isPaginaDeLogin());
        Assert.assertFalse(loginPage.contemTexto("Dados do Leilão"));
    }

}
