package home.four.you.repository;

import home.four.you.model.entity.Role;
import home.four.you.security.auth.authorization.AuthorityRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link Role} entity.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds role by provided name.
     *
     * @param role Role name.
     * @return Matching role.
     */
    Role findByName(AuthorityRole name);
}
