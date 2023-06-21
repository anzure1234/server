package ams.security;

import ams.constant.AppConstant;
import ams.model.entity.User;
import ams.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> accountOptional = accountService.findByAccount(username);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Account: \"" + username + "\" is not exist");
        }
        User user = accountOptional.get();
        List<GrantedAuthority> roles = Collections.singletonList(
                new SimpleGrantedAuthority(AppConstant.USER_ROLE_PREFIX + user.getRole().name())
        );

        return new org.springframework.security.core.userdetails.User(user.getAccount(),
                user.getPassword(), roles);
    }
}
