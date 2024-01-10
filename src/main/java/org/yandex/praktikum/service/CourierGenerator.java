package org.yandex.praktikum.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.yandex.praktikum.model.courier.courierCreate.Courier;
import org.yandex.praktikum.model.courier.courierCreate.CourierWithoutLogin;
import org.yandex.praktikum.model.courier.courierCreate.CourierWithoutPassword;
import org.yandex.praktikum.model.courier.courierCreate.CourierWithoutName;

public class CourierGenerator {
    public Courier getCourier() {
        Courier courier = new Courier();
        // генерация буквенно-цифровой строки размером от 5 до 30 символов
        courier.setLogin(RandomStringUtils.randomAlphanumeric(5,30));
        courier.setPassword(RandomStringUtils.randomAlphanumeric(10));
        // генерация алфавитной строки
        courier.setFirstName(RandomStringUtils.randomAlphabetic(5, 25));

        return courier;
    }

    public CourierWithoutPassword getCourierWithoutPassword() {
        CourierWithoutPassword courierWithoutPassword = new CourierWithoutPassword();
        courierWithoutPassword.setFirstName(RandomStringUtils.randomAlphabetic(5, 25));
        courierWithoutPassword.setLogin(RandomStringUtils.randomAlphanumeric(5, 25));

        return courierWithoutPassword;
    }

    public CourierWithoutLogin getCourierWithoutLogin() {
        CourierWithoutLogin courierWithoutLogin = new CourierWithoutLogin();
        courierWithoutLogin.setFirstName(RandomStringUtils.randomAlphabetic(5, 25));
        courierWithoutLogin.setPassword(RandomStringUtils.randomAlphanumeric(10));

        return new CourierWithoutLogin();
    }

    public CourierWithoutName getCourierWithoutName() {
        CourierWithoutName courierWithoutName = new CourierWithoutName();
        courierWithoutName.setLogin(RandomStringUtils.randomAlphanumeric(5, 25));
        courierWithoutName.setPassword(RandomStringUtils.randomAlphanumeric(5, 25));

        return courierWithoutName;
    }

}
