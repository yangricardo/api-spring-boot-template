package tech.yang.api_spring_boot.resources.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.yang.api_spring_boot.resources.auth.model.CreateUserDTO;
import tech.yang.api_spring_boot.resources.users.model.User;
import tech.yang.api_spring_boot.resources.users.services.IUserService;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthenticationService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("User not found");
    }

    public User createUser(CreateUserDTO createUserDTO) throws Exception {
        Optional<User> userFound = this.userService.findByEmail(createUserDTO.getEmail());
        if (userFound.isPresent()) {
            throw new Exception("Email alreary Taken");
        }
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        return userService.create(user);
    }

}
