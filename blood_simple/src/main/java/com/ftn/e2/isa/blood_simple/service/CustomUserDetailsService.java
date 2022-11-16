package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return user;
        }
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            return false;
        }

        User user = userRepository.findByEmail(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        Date date = new Date();
        // TODO: Delete or implement it
        //user.setLastPasswordResetDate(new Timestamp(date.getTime()));
        //user.setNumberOfLogIns(1);
        userRepository.save(user);
        return true;
    }

}
