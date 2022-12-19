package com.bruno.parkingspot.services.implementations;

import com.bruno.parkingspot.models.UserModel;
import com.bruno.parkingspot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        return new User(userModel.getUsername(),userModel.getPassword(),true,
                true, true, true, userModel.getAuthorities());
    }

    public UserModel findByUsername(String username){
        UserModel user = userRepository.findByUsername(username).get();
        return user;
    }

    public String registerNewUser(UserModel user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        user = findByUsername(user.getUsername());
        String data = user.getUsername();
        return data;
    }
}
