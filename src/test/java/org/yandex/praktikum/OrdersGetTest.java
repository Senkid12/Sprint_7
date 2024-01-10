package org.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

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
        response.then().statusCode(200).and().body("orders", notNullValue());
    }

    @Step("Полуение списка заказов")
    public Response getOrders() {
        Response response =
                given()
                        .get("/api/v1/orders");
        return response;
    }

}
