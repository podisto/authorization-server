package com.simba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by podisto on 09/10/2021.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(this::getUser)
                .orElseThrow(() -> new UsernameNotFoundException("Username " +username+ " doesn't exist."));
    }

    private User getUser(Account a) {
        return new User(a.getUsername(), encoder.encode(a.getPassword()),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("write, read"));
    }
}
