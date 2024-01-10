package org.yandex.praktikum.model.courier.courierForAuthorization;

import org.yandex.praktikum.model.courier.courierCreate.Courier;

public class CourierForAuthorizationWithoutLogin {
    private String password;

    public CourierForAuthorizationWithoutLogin(String password) {
        this.password = password;
    }
    public CourierForAuthorizationWithoutLogin (Courier courier) {
        this.password = courier.getPassword();
    }

    public CourierForAuthorizationWithoutLogin() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
