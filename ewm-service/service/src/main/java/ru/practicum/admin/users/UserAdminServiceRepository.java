package ru.practicum.admin.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.users.User;

public interface UserAdminServiceRepository extends JpaRepository<User, Long> {
}
