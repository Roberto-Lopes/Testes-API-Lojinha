package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    @DisplayName("Setar path da API e buscar Token")
    public void beforeEach(){
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        this.token =
                given()
                    .contentType(ContentType.JSON)
                    .body(UsuarioDataFactory.setarUsuario("admin", "admin"))
                .when()
                    .post("/v2/login")
                .then()
                    .extract()
                        .path("data.token");
    }

    @Test
    @DisplayName("Validar inserção de produto com valor maior que permitido")
    public void testValidarInsercaoDeProdutoComValorMaiorQuePermitido(){

        // Tentar inserir um produto com valor 7001.00 e validar que a mensagem de erro foi apresentada e o status code retornado foi 422
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7001))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Validar inserção de produto com valor menor que permitido")
    public void testValidarInsercaoDeProdutoComValorMenorQuePermitido(){

        // Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada e o status code retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0))
                .when()
                .post("/v2/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Validar inserção de novo produto")
    public void testValidarInsercaoDeNovoProduto(){

        // Tentar inserir um novo produto válido
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(500))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("message", equalTo("Produto adicionado com sucesso"))
                .statusCode(201);
    }
}
