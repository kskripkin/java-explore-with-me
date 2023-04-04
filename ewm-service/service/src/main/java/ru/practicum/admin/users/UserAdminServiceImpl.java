package ru.practicum.admin.users;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.users.User;
import ru.practicum.validation.ValidateEvents;
import ru.practicum.validation.ValidateUsers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;

    private final ValidateUsers validateUsers;

    private final ValidateEvents validateEvents;

    @Override
    public List<User> getUsers(Integer[] ids, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        return userRepository.getUsers(ids, PageRequest.of((from / size), size));
    }

    @Override
    public User addUser(User user) {
        validateUsers.validateObject(user);
        validateUsers.validateUniqueName(user);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
