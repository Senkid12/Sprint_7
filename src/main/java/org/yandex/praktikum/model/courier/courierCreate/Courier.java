package org.yandex.praktikum.model.courier.courierCreate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Courier {
    private String login;
    private String password;
    private String firstName;
}
