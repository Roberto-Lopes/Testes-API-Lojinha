package modulos.usuario;

import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.apache.http.impl.cookie.PublicSuffixDomainFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest no modulo Usuario")
public class UsuarioTest {
    @BeforeEach
    @DisplayName("Setar path da API")
    public void beforeEach(){
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";
    }

    @Test
    @DisplayName("Tentar cadastrar novo usuario")
    public void testTentarCadastrarNovoUsuario(){
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(10000);
        String login = "robertoLopes" + Integer.toString(rndNumber);

        // Tentar cadastrar um novo usuario
        given()
            .contentType(ContentType.JSON)
            .body(UsuarioDataFactory.criarNovoUsuario("Roberto Lopes", login, "roberto123"))
        .when()
            .post("/v2/usuarios")
        .then()
            .assertThat()
                .body("message", equalTo("Usuário adicionado com sucesso"))
                .statusCode(201);

        System.out.println("\nLogin: " + login);
    }
}
