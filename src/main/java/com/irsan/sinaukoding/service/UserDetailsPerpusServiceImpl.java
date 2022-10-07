package com.irsan.sinaukoding.service;

import com.irsan.sinaukoding.entity.UserPerpus;
import com.irsan.sinaukoding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsPerpusServiceImpl implements UserDetailsPerpusService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<UserPerpus> employee = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (employee.isPresent()) {
            return new User(employee.get().getUsername(), employee.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Employee not found with username: " + usernameOrEmail);
        }
    }
}
