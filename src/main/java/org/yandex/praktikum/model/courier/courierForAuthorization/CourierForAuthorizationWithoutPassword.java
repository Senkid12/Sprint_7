package org.yandex.praktikum.model.courier.courierForAuthorization;

import org.yandex.praktikum.model.courier.courierCreate.Courier;

public class CourierForAuthorizationWithoutPassword {
    private String login;

    public CourierForAuthorizationWithoutPassword(String login) {
        this.login = login;
    }

    public CourierForAuthorizationWithoutPassword() {
    }
    public CourierForAuthorizationWithoutPassword(Courier courier) {
        this.login = courier.getLogin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
