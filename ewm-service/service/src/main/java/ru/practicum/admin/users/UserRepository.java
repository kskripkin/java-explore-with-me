package ru.practicum.admin.users;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.users.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * " +
            "from users " +
            "where id in (?1) ", nativeQuery = true)
    List<User> getUsers(Integer[] ids, Pageable pageable);
}
