package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.Role;
import me.siyoon.noticeboard.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByStatus(Status status);
}
