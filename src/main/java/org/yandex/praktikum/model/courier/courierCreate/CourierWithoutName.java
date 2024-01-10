package org.yandex.praktikum.model.courier.courierCreate;

public class CourierWithoutName {
    private String login;
    private String password;

    public CourierWithoutName(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierWithoutName() {
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
