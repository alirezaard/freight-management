package com.company.logistics.freight.services;



import com.company.logistics.freight.domain.User;
import com.company.logistics.freight.dto.UserDto;
import com.company.logistics.freight.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@AllArgsConstructor
public class UserServices implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("کاربر یافت نشد");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toString()) // نقش‌ها (Authorities)
                .build();

    }

    public List<UserDto> getAllUsers() {
        final List<User> all = userRepository.findAll();
        final List<UserDto> userDTOs = new ArrayList<>();
        for (User user : all) {
            userDTOs.add(UserDto.user2Dto(user));
        }
        return userDTOs;
    }

    public User insertUser(UserDto userDto) throws Exception {
        if(userRepository.existsUserByUsername(userDto.getUsername()))
            throw new Exception("این کاربر وجود دارد.");
        final User user = UserDto.dto2User(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean updateUser(UserDto userDto) {
        try {
            final User user = userRepository.findByUsername(userDto.getUsername());
            if (user == null)
                return false;
//            if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword()))
//                return false;
            if (StringUtils.hasText(userDto.getPassword()))
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(String username) {
        try {
            userRepository.deleteUserByUsername(username);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}