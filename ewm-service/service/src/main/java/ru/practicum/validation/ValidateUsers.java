package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.exception.model.NotFoundException;

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
}
