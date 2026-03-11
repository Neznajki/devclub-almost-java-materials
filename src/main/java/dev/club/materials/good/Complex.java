package dev.club.materials.good;

import dev.club.materials.dto.User.UserDTO;

import static dev.club.materials.contract.Constants.*;

public class Complex {
    public void checkConstants(UserDTO userDTO) {
        if (userDTO.auth.roleId.equals(USER_ROLE) && userDTO.auth.username.equals("superAdmin")) {
            throw new RuntimeException("Admin user cannot have USER_ROLE");
        }

        if (userDTO.auth.roleId.equals(USER_ROLE)) {
            //..
            return;
        }

        if (userDTO.auth.roleId.equals(SUPER_ADMIN_ROLE) && userDTO.auth.username.equals("admin")) {
            throw new RuntimeException("Admin user cannot have USER_ROLE");
        }

        //..
        if (userDTO.auth.password.equals("password check")) {
            throw new RuntimeException("Password cannot be 'password check'");
        }
    }
}
