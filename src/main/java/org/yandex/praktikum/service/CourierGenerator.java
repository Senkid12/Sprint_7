package org.yandex.praktikum.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.yandex.praktikum.model.courier.courierCreate.Courier;

public class CourierGenerator {
    public Courier getCourier() {
        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(5, 30))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .firstName(RandomStringUtils.randomAlphabetic(5, 25))
                .build();
    }

    public Courier getCourierWithoutPassword() {
        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(5, 30))
                .firstName(RandomStringUtils.randomAlphabetic(5, 25))
                .build();
    }

    public Courier getCourierWithoutLogin() {
        return Courier.builder()
                .password(RandomStringUtils.randomAlphanumeric(5, 30))
                .firstName(RandomStringUtils.randomAlphabetic(5, 25))
                .build();
    }

    public Courier getCourierWithoutName() {
        return Courier.builder()
                .login(RandomStringUtils.randomAlphanumeric(5, 30))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .build();
    }
}
