package ams.repository;

import ams.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<User, String> {

    Optional<User> findByAccountIgnoreCase(String account);
}
