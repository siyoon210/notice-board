package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
