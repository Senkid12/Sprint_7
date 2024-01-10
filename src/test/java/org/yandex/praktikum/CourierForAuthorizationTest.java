package org.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.yandex.praktikum.model.courier.courierCreate.Courier;
import org.yandex.praktikum.model.courier.courierForAuthorization.CourierForAuthorization;
import org.yandex.praktikum.model.courier.courierForAuthorization.CourierForAuthorizationWithoutLogin;
import org.yandex.praktikum.model.courier.courierForAuthorization.CourierForAuthorizationWithoutPassword;
import org.yandex.praktikum.service.CourierForAuthorizationGenerator;
import org.yandex.praktikum.service.CourierGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CourierForAuthorizationTest {
    private final CourierForAuthorizationGenerator courierForAuthorizationGenerator = new CourierForAuthorizationGenerator();
    private final CourierGenerator courierGenerator = new CourierGenerator();
    private CourierForAuthorization courierForAuthorization;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        Courier courier = courierGenerator.getCourier();
        Response response = create(courier);
        courierForAuthorization = courierForAuthorizationGenerator.getCourierForAuthorization(courier);

    }
    // Подумал может есть какой-то способ как-то правильней выводить информацию с теста
    // (думаю что sout не используются в тестах), буду благодарен за подсказку как это грамотней сделать)
    @Test
    @DisplayName("Авторизация курьера")
    public void courierLogin() {
        Response response = authorizationCourier(courierForAuthorization);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация без поля login")
    public void courierLoginWithoutLogin() {
        CourierForAuthorizationWithoutLogin courierForAuthorizationWithoutLogin = courierForAuthorizationGenerator.getCourierForAuthorizationWithoutLogin(courierGenerator.getCourier());
        Response response = authorizationCourierWithoutLogin(courierForAuthorizationWithoutLogin);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    // В этом тесте не досткпен сервер, проверял через postman тоже самое
    @Test
    @DisplayName("Авторизация без поля password")
    public void courierLoginWithoutPassword() {
        CourierForAuthorizationWithoutPassword courierForAuthorizationWithoutPassword = courierForAuthorizationGenerator.getCourierForAuthorizationWithoutPassword(courierGenerator.getCourier());
        Response response = authorizationCourierWithoutPassword(courierForAuthorizationWithoutPassword);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера с полем login = null")
    public void courierLoginWithLoginNull() {
        CourierForAuthorization courierForAuthorizationWithLoginNull = courierForAuthorizationGenerator.getCourierForAuthorizationWithLoginNull(courierGenerator.getCourier());
        Response response = authorizationCourier(courierForAuthorizationWithLoginNull);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    // В этом тесте не досткпен сервер, проверял через postman тоже самое
    @Test
    @DisplayName("Авторизация курьера с полем password = null")
    public void courierLoginWithPasswordNull() {
        CourierForAuthorization courierForAuthorizationWithPasswordNull = courierForAuthorizationGenerator.getCourierForAuthorizationWithPasswordNull(courierGenerator.getCourier());
        Response response = authorizationCourier(courierForAuthorizationWithPasswordNull);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Создание курьера")
    public Response create(Courier courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post("/api/v1/courier");
        return response;
    }

    @Step("Авторизация курьера")
    public Response authorizationCourier(CourierForAuthorization courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");
        return response;
    }

    @Step("Авторизация курьера без поля login")
    public Response authorizationCourierWithoutLogin(CourierForAuthorizationWithoutLogin courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");
        return response;
    }

    @Step("Авторизация курьера без поля password")
    public Response authorizationCourierWithoutPassword(CourierForAuthorizationWithoutPassword courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");
        return response;
    }

}
