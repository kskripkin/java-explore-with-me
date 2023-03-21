package ru.practicum.admin.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.users.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserAdminServiceRepository userAdminServiceRepository;

    @Override
    public List<User> getUsers(Integer[] ids, Integer from, Integer size) {
        return userAdminServiceRepository.getUsers(ids, PageRequest.of((from / size), size));
    }

    @Override
    public User addUser(User user) {
        return userAdminServiceRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userAdminServiceRepository.delete(userId);
    }
}
