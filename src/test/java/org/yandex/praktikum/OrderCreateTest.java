package org.yandex.praktikum;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yandex.praktikum.model.order.Order;
import org.yandex.praktikum.service.OrderGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    Logger logger = LoggerFactory.getLogger(OrderCreateTest.class);
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private Integer trackId;
    private final OrderGenerator orderGenerator = new OrderGenerator();

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters
    public static Object[][] getOrders() {
        return new Object[][]{
                {"Sanji", "Vinsmoke", "North blue", "1", "+79005664023", 2, "20-01-2024", "-", new String[]{"BLACK"}},
                {"Zoro", "Roronoa", "East blue", "2", "+79005146423", 10, "21-01-2024", "Comment for Zoro", new String[]{"BLACK", "GREY"}},
                {"Luffy", "Monkey D", "Fuusya", "3", "+79005146423", 5, "22-01-2024", "Comment for Zoro", new String[]{"GREY"}},
                {"Chopper", "Tony Tony", "Drum Island", "10", "79005144245", 1, "22-01-2024", "Comment for Chopper", new String[]{}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    // Подумал может есть какой-то способ как-то правильней выводить информацию с теста
    // (думаю что sout не используются в тестах), буду благодарен за подсказку как это грамотней сделать)
    @Test
    @DisplayName("Создание заказа")
    public void createOrder() {
        Order order = orderGenerator.getOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        System.out.println("Создан заказ от: " + order.getFirstName());
        Response response = create(order);
        System.out.println("Ответ от сервера: " + response.body().asString());
        trackId = response.body().path("track");
        System.out.println("Заказ с id " + trackId + " создан");
        response.then().assertThat().body("track", notNullValue()).and().statusCode(201) ;
    }

    @Step("Создание заказа")
    public Response create(Order order) {
        Response response =
                given()
                        .body(order)
                        .when()
                        .post("/api/v1/orders");
        return response;
    }
}
