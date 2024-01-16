package org.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yandex.praktikum.model.courier.courierCreate.Courier;
import org.yandex.praktikum.service.CourierForAuthorizationGenerator;
import org.yandex.praktikum.service.CourierGenerator;
import org.yandex.praktikum.service.DeleteCourierMethod;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.yandex.praktikum.constants.Endpoints.END_POINT_FOR_COURIER_AUTHORIZATION;
import static org.yandex.praktikum.constants.Endpoints.END_POINT_FOR_CREATE_COURIER;

public class CourierForAuthorizationTest {
    private final CourierForAuthorizationGenerator courierForAuthorizationGenerator = new CourierForAuthorizationGenerator();
    private final CourierGenerator courierGenerator = new CourierGenerator();
    private final DeleteCourierMethod deleteCourierMethod = new DeleteCourierMethod();
    private Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        courier = courierGenerator.getCourier();
        Response response = create(courier);

    }

    @Test
    @DisplayName("Авторизация курьера")
    public void courierLogin() {
        Response response = authorizationCourier(courierForAuthorizationGenerator.getCourierForAuthorization(courier));
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().statusCode(HttpStatus.SC_OK).and().assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация без поля login")
    public void courierLoginWithoutLogin() {
        Courier courierForAuthorizationWithoutLogin = courierForAuthorizationGenerator.getCourierForAuthorizationWithoutLogin(courier);
        Response response = authorizationCourierWithoutLogin(courierForAuthorizationWithoutLogin);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера с полем login = null")
    public void courierLoginWithLoginNull() {
        Courier courierForAuthorizationWithLoginNull = courierForAuthorizationGenerator.getCourierForAuthorizationWithLoginNull(courier);
        Response response = authorizationCourier(courierForAuthorizationWithLoginNull);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера с полем password = null")
    public void courierLoginWithPasswordNull() {
        Courier courierForAuthorizationWithPasswordNull = courierForAuthorizationGenerator.getCourierForAuthorizationWithPasswordNull(courier);
        Response response = authorizationCourier(courierForAuthorizationWithPasswordNull);
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @After
    @DisplayName("Удаление курьера")
    public void deleteCourier() {
        if (courier != null) {
            deleteCourierMethod.deleteCourier(courier);
        }
    }

    @Step("Создание курьера")
    public Response create(Courier courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(END_POINT_FOR_CREATE_COURIER);
        return response;
    }

    @Step("Авторизация курьера")
    public Response authorizationCourier(Courier courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(END_POINT_FOR_COURIER_AUTHORIZATION);
        return response;
    }

    @Step("Авторизация курьера без поля login")
    public Response authorizationCourierWithoutLogin(Courier courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post(END_POINT_FOR_COURIER_AUTHORIZATION);
        return response;
    }
}
