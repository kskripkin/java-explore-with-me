package ru.practicum.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserRequest {

    String email;

    String name;
}
