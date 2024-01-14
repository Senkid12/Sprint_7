package org.yandex.praktikum.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.yandex.praktikum.model.courier.courierCreate.Courier;

public class CourierGenerator {
    public Courier getCourier() {
        //Courier courier = new Courier();


        // генерация буквенно-цифровой строки размером от 5 до 30 символов
//        courier.setLogin(RandomStringUtils.randomAlphanumeric(5, 30));
//        courier.setPassword(RandomStringUtils.randomAlphanumeric(10));
//        // генерация алфавитной строки
//        courier.setFirstName(RandomStringUtils.randomAlphabetic(5, 25));

        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(5, 30))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .firstName(RandomStringUtils.randomAlphabetic(5, 25))
                .build();
    }

    public Courier getCourierWithoutPassword() {
//        CourierWithoutPassword courierWithoutPassword = new CourierWithoutPassword();
//        courierWithoutPassword.setFirstName(RandomStringUtils.randomAlphabetic(5, 25));
//        courierWithoutPassword.setLogin(RandomStringUtils.randomAlphanumeric(5, 25));

        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(5, 30))
                .firstName(RandomStringUtils.randomAlphabetic(5, 25))
                .build();
    }

    public Courier getCourierWithoutLogin() {
//        CourierWithoutLogin courierWithoutLogin = new CourierWithoutLogin();
//        courierWithoutLogin.setFirstName(RandomStringUtils.randomAlphabetic(5, 25));
//        courierWithoutLogin.setPassword(RandomStringUtils.randomAlphanumeric(10));

        return Courier.builder()
                .password(RandomStringUtils.randomAlphanumeric(5, 30))
                .firstName(RandomStringUtils.randomAlphabetic(5, 25))
                .build();
    }

    public Courier getCourierWithoutName() {
//        CourierWithoutName courierWithoutName = new CourierWithoutName();
//        courierWithoutName.setLogin(RandomStringUtils.randomAlphanumeric(5, 25));
//        courierWithoutName.setPassword(RandomStringUtils.randomAlphanumeric(5, 25));

        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(5, 30))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .build();
    }

}
