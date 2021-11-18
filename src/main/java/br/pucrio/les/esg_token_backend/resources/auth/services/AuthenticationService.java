package br.pucrio.les.esg_token_backend.resources.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.pucrio.les.esg_token_backend.resources.users.model.User;
import br.pucrio.les.esg_token_backend.resources.users.services.IUserService;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private IUserService userService;

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

}
