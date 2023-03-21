package ru.practicum.adminUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.adminUsers.model.User;

public interface UserAdminServiceRepository extends JpaRepository<User, Long> {
}
