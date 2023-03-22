package ru.practicum.admin.users;

import ru.practicum.model.users.User;

import java.util.List;

public interface UserAdminService {

    List<User> getUsers(Integer[] ids,
                        Integer from,
                        Integer size);

    User addUser(User user);

    void deleteUser(Long userId);
}
