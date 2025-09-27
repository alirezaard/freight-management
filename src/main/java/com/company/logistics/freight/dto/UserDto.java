package com.company.logistics.freight.dto;

import com.company.logistics.freight.domain.Roles;
import com.company.logistics.freight.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Roles role;

    public static UserDto user2Dto(User user) {
        UserDto userDto = new UserDto();
        userDto.username = user.getUsername();
        userDto.password = "*****";
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.email = user.getEmail();
        userDto.phone = user.getPhone();
        userDto.address = user.getAddress();
        userDto.role = user.getRole();
        return userDto;
    }

    public static User dto2User(UserDto dto){
        final User user = new User();
        user.setUsername(dto.username);
        user.setPassword(dto.password);
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        user.setEmail(dto.email);
        user.setPhone(dto.phone);
        user.setAddress(dto.address);
        user.setRole(dto.role);
        return user;
    }
}
