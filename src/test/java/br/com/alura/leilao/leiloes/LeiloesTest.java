package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LeiloesTest {


    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage paginaCadastro;

    @AfterEach
    public void afterEach(){
        leiloesPage.fechar();
    }

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        leiloesPage = paginaDeLogin.efetuaLogin();
        paginaCadastro = leiloesPage.carregaFormulario();
    }

    @Test
    public void deveriaCadastrarLeilao() {


        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";
        leiloesPage = paginaCadastro.cadastrarLeilao(nome, valor, hoje);

        Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));

    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {

        leiloesPage = paginaCadastro.cadastrarLeilao("", "", "");

        Assert.assertFalse(paginaCadastro.isPaginaAtual());
        Assert.assertTrue(leiloesPage.isPaginaAtual());
        Assert.assertTrue(paginaCadastro.isMsgErroVisivel());
    }

}
