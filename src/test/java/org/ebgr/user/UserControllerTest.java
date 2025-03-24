package org.ebgr.user;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
class UserControllerTest {
    @Test
    void test_GetNullUserName() {
        given()
          .when().get("/user")
          .then()
             .statusCode(200)
             .body(is("Logged as user (null)."));
    }

    @Test
    void test_AuthenticateSuccesfully() {
        String body = "{\"login\":\"erbert\",\"password\":\"1234\"}";
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .when().post("/user")
            .then()
                .statusCode(200)
                .body(is(body));
    }

}