package ams.repository;

import ams.model.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long>{

    Optional<User> findByIdAndDeletedFalse(Long id);
}
