package org.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.yandex.praktikum.constants.Endpoints.endpointForCreateOrder;

public class OrdersGetTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderByTrack() {
        Response response = getOrders();
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().statusCode(HttpStatus.SC_OK).and().body("orders", notNullValue());
    }

    @Step("Получение списка заказов")
    public Response getOrders() {
        Response response =
                given()
                        .get(endpointForCreateOrder);
        return response;
    }

}
