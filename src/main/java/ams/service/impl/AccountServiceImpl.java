package ams.service.impl;

import ams.repository.AccountRepository;
import ams.model.entity.User;
import ams.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<User> findByAccount(String account) {
        return accountRepository.findByAccountIgnoreCase(account);
    }
}
