package org.yandex.praktikum.service;

import org.yandex.praktikum.model.courier.courierCreate.Courier;

public class CourierForAuthorizationGenerator {
    public Courier getCourierForAuthorization(Courier courier) {
        return Courier.builder()
                .login(courier.getLogin())
                .password(courier.getPassword())
                .build();
    }

    public Courier getCourierForAuthorizationWithoutLogin(Courier courier) {
        return Courier.builder()
                .password(courier.getPassword())
                .build();
    }

    public Courier getCourierForAuthorizationWithLoginNull(Courier courier){
        return Courier.builder()
                .login(null)
                .password(courier.getPassword())
                .build();
    }

    public Courier getCourierForAuthorizationWithPasswordNull(Courier courier){
        return Courier.builder()
                .login(courier.getLogin())
                .password("")
                .build();
    }
}
