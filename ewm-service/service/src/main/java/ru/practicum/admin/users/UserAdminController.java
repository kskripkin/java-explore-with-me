package ru.practicum.admin.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.users.User;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/users")
public class UserAdminController {

    private final UserAdminService userAdminService;

    @GetMapping
    public List<User> getUsers(@RequestParam(name = "ids", required = false) Integer[] ids,
                               @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                               @RequestParam(value= "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("GET /admin/users?ids={}&from={}&size={}", ids, from, size);
        return userAdminService.getUsers(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User addUser(@RequestBody User user) {
        log.info("POST /admin/users {}", user);
        return userAdminService.addUser(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        log.info("DELETE /admin/users/{}", userId);
        userAdminService.deleteUser(userId);
    }

}
