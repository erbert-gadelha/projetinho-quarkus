package org.ebgr.user;

import java.time.LocalDateTime;

import org.ebgr.DTO.UserDTO;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    static String randomString;

    @Test
    void test_GetNullUserName() {
        given()
          .when().get("/user")
          .then()
             .statusCode(200)
             .body(is("Logged as user (null)."));
    }

    @Test
    void test_CreateUser_Successfully() {
        randomString = String.format("%d%d", LocalDateTime.now().getSecond(), LocalDateTime.now().getNano());
        UserDTO dto = new UserDTO("test-user", randomString, "1234");
        
        given()
          .when()
          .contentType("application/json")
          .body(dto.toString())
          .post("/user")
            .then()
                .statusCode(201);
    }

    @Test
    void test_CreateUser_Unsuccessfully() {
        UserDTO dto = new UserDTO("test-user", randomString, "1234");

        given()
        .when()
        .contentType("application/json")
        .body(dto.toString())
        .post("/user")
            .then()
                .statusCode(400);
    }

    @Test
    void test_AuthenticateSuccesfully() {
        UserDTO dto = new UserDTO("test-user", randomString, "1234");
                
        given()
            .when()
            .contentType("application/json")
            .body(dto.toString())
            .post("/user/authenticate")
            .then()
                .statusCode(200);
    }

}