package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.exception.model.ConflictException;
import ru.practicum.exception.model.NotFoundException;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.model.users.User;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateUsers {

    private final UserRepository userRepository;

    public void findUser(long userId) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("User not found");
        }
    }

    public void validateObject(User user) {
        if (user.getName() == null || user.getEmail() == null) {
            throw new ValidationException("User not valid");
        }
    }

    public void validateUniqueName(User user) {
        if (!userRepository.getUsersByName(user.getName()).isEmpty()) {
            throw new ConflictException("User already used");
        }
    }
}
