package ams.service;

import ams.model.entity.User;

import java.util.Optional;

public interface AccountService {
    Optional<User> findByAccount(String account);
}
