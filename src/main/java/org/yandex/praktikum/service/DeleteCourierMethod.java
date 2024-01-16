package org.yandex.praktikum.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.yandex.praktikum.model.courier.courierCreate.Courier;

import static io.restassured.RestAssured.given;

public class DeleteCourierMethod {
    public Integer getCourierId(Courier courier) {
        Courier courierForAuthorization = new CourierForAuthorizationGenerator().getCourierForAuthorization(courier);
        Response response = authorizationCourier(courierForAuthorization);
        return response.body().path("id");
    }

    public void deleteCourier(Courier courier) {
        int courierId = getCourierId(courier);
        Response response = delete(courierId);
    }

    @Step("Авторизация курьера")
    public Response authorizationCourier(Courier courier) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .when()
                        .post("/api/v1/courier/login");
        return response;
    }

    @Step("Удаление курьера")
    public Response delete(Integer courierId) {
        Response response =
                given()
                        .delete("/api/v1/courier/" + String.format("%d", courierId));
        return response;
    }
}
