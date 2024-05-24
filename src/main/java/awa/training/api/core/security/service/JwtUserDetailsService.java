package awa.training.api.core.security.service;

import awa.training.api.core.security.vo.JwtResponse;
import awa.training.api.feature.users.entity.UsersEntity;
import awa.training.api.feature.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsersEntity user = usersRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return new User(username, "", new ArrayList<>());
        }
        return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),
                new ArrayList<>());
    }

    public JwtResponse.Response buildJwtResponse(String token, UsersEntity usersEntity) {
        JwtResponse.Response response = new JwtResponse.Response();
        response.setToken(token);
        response.setFullName(usersEntity.getFullName());
        response.setNickName(usersEntity.getNickName());
        response.setUsername(usersEntity.getUsername());
        return response;
    }

}
