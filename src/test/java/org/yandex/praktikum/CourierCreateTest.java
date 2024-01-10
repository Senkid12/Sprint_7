package org.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yandex.praktikum.model.courier.courierCreate.Courier;
import org.yandex.praktikum.model.courier.courierCreate.CourierWithoutLogin;
import org.yandex.praktikum.model.courier.courierCreate.CourierWithoutName;
import org.yandex.praktikum.model.courier.courierCreate.CourierWithoutPassword;
import org.yandex.praktikum.service.CourierGenerator;
import org.yandex.praktikum.service.DeleteCourierMethod;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest {
    public final CourierGenerator courierGenerator = new CourierGenerator();
    private Courier courier;
    private final DeleteCourierMethod deleteCourierMethod = new DeleteCourierMethod();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    // Подумал может есть какой-то способ как-то правильней выводить информацию с теста
    // (думаю что sout не используются в тестах), буду благодарен за подсказку как это грамотней сделать)
    @Test
    @DisplayName("Создание курьера с тремя параметрами (логин, пароль, имя)")
    public void createCourier() {
        Response response = create(courierGenerator.getCourier());
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера с существующим логином")
    public void createCourierWithAnExistingLogin() {
        courier = courierGenerator.getCourier();
        Response response = create(courier);
        Response response1 = create(courier);

        // Небольшая неточность в документации к API
        // (В запросе с повторяющимся логином в ключе 'message' - значение 'Этот логин уже используется',
        // а по факту 'Этот логин уже используется. Попробуйте другой.')
        System.out.println("Ответ с сервера " + response1.body().asString());
        response1.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без поля с паролем")
    public void createCourierWithoutPassword() {
        Response response = createWithoutPassword(courierGenerator.getCourierWithoutPassword());
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без поля с логином")
    public void createCourierWithoutLogin() {
        Response response = createWithoutLogin(courierGenerator.getCourierWithoutLogin());
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без поля с именем")
    public void createCourierWithoutName() {
        Response response = createWithoutName(courierGenerator.getCourierWithoutName());
        System.out.println("Ответ с сервера " + response.body().asString());
        response.then().assertThat().body("ok", equalTo(true));
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
                        .post("/api/v1/courier");
        return response;
    }

    @Step("Создание курьера без поля password")
    public Response createWithoutPassword(CourierWithoutPassword courierWithoutPassword) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierWithoutPassword)
                        .when()
                        .post("/api/v1/courier");
        return response;
    }

    @Step("Создание курьера без поля login")
    public Response createWithoutLogin(CourierWithoutLogin courierWithoutLogin) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierWithoutLogin)
                        .when()
                        .post("/api/v1/courier");

        return response;
    }

    @Step("Создание курьера без поля firstName")
    public Response createWithoutName (CourierWithoutName courierWithoutName) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierWithoutName)
                        .when()
                        .post("/api/v1/courier");
        return response;
    }
//    @Step("Удаление курьера")
//    public Response deleteCourier(Integer courierId) {
//        Response response =
//                given()
//                        .delete("/api/v1/courier/" + String.format("%d", courierId));
//        return response;
//    }

}