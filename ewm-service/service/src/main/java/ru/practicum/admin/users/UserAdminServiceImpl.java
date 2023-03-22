package ru.practicum.admin.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.global.user.UserServiceRepository;
import ru.practicum.model.users.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserServiceRepository userServiceRepository;

    @Override
    public List<User> getUsers(Integer[] ids, Integer from, Integer size) {
        return userServiceRepository.getUsers(ids, PageRequest.of((from / size), size));
    }

    @Override
    public User addUser(User user) {
        return userServiceRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userServiceRepository.deleteById(userId);
    }
}
