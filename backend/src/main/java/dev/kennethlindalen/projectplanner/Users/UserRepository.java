package dev.kennethlindalen.projectplanner.Users;

import dev.kennethlindalen.projectplanner.Users.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByUsername(String username);
    UserDAO findByEmail(String email);
    UserDAO findByUuid(String uuid);
}
