package org.ebgr.message;

import org.ebgr.DTO.MessageDTO;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageControllerTest {

    @Test
    @Order(1)
    void test_GetMessage() {
        given()
          .when().get("/message")
          .then()
             .statusCode(200);
    }

    @Test
    @Order(2)
    void test_PostMessageUnssuccesfully() {
        final String CONTENT = "Hello, World!!!";
        final String SENDER = null;
        final String BODY = String.format("{\"content\":\"%s\"}", CONTENT);


        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BODY)
            .when().post("/message")
            .then()
                .statusCode(200)
                .body("content", not(CONTENT))
                .body("sender", not(SENDER));
    }

    @Test
    void test_PostMessageSuccesfully() {
        final String CONTENT = "Olá, amigos!! Como vão?";
        final String SENDER = "Alan";
        final MessageDTO MESSAGE = new MessageDTO(SENDER, CONTENT, "");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(MESSAGE.toString())
            .when().post("/message")
            .then()
                .statusCode(200)
                .body("content", equalTo(CONTENT))
                .body("sender", equalTo(SENDER));
    }


}