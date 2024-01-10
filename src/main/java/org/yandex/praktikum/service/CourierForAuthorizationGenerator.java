package org.yandex.praktikum.service;

import org.yandex.praktikum.model.courier.courierCreate.Courier;
import org.yandex.praktikum.model.courier.courierForAuthorization.CourierForAuthorization;
import org.yandex.praktikum.model.courier.courierForAuthorization.CourierForAuthorizationWithoutLogin;
import org.yandex.praktikum.model.courier.courierForAuthorization.CourierForAuthorizationWithoutPassword;

public class CourierForAuthorizationGenerator {
    public CourierForAuthorization getCourierForAuthorization(Courier courier) {
        return new CourierForAuthorization(courier.getLogin(), courier.getPassword());
    }

    public CourierForAuthorizationWithoutLogin getCourierForAuthorizationWithoutLogin(Courier courier) {
        return new CourierForAuthorizationWithoutLogin(courier);
    }

    public CourierForAuthorizationWithoutPassword getCourierForAuthorizationWithoutPassword(Courier courier) {
        return new CourierForAuthorizationWithoutPassword(courier);
    }

    public CourierForAuthorization getCourierForAuthorizationWithLoginNull(Courier courier){
        CourierForAuthorization courierForAuthorization = new CourierForAuthorization();
        courierForAuthorization.setPassword(courier.getPassword());
        return courierForAuthorization;
    }

    public CourierForAuthorization getCourierForAuthorizationWithPasswordNull(Courier courier){
        CourierForAuthorization courierForAuthorization = new CourierForAuthorization();
        courierForAuthorization.setLogin(courier.getLogin());
        return courierForAuthorization;
    }
}
