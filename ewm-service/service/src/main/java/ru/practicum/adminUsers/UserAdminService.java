package ru.practicum.adminUsers;

import ru.practicum.adminUsers.model.User;

import java.util.List;

public interface UserAdminService {

    List<User> getUsers(Integer[] ids,
                        Integer from,
                        Integer size);

    User addUser(User user);

    void deleteUser(Integer userId);
}
