package org.yandex.praktikum.model.courier.courierForAuthorization;

public class CourierForAuthorization {
    private String login;
    private String password;

    public CourierForAuthorization(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierForAuthorization() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
