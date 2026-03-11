package dev.club.materials.spaghetti;

import dev.club.materials.dto.UserSpaghettiDTO;

import static dev.club.materials.contract.Constants.SUPER_ADMIN_ROLE;
import static dev.club.materials.contract.Constants.USER_ROLE;

public class Complex {
    public void checkConstants(UserSpaghettiDTO userSpaghettiDTO) {
        if (
            userSpaghettiDTO.roleId.equals(USER_ROLE)
        ) {
            if (userSpaghettiDTO.username.equals("superAdmin")) {
                throw new RuntimeException("Admin user cannot have USER_ROLE");
            } else {
                //..
                return;
            }
        } else if (userSpaghettiDTO.roleId.equals(SUPER_ADMIN_ROLE)) {
            if (userSpaghettiDTO.username.equals("admin")) {
                throw new RuntimeException("Admin user cannot have USER_ROLE");
            } else {
                //..
            }
        }

        if (userSpaghettiDTO.password.equals("password check")) {
            throw new RuntimeException("Password cannot be 'password check'");
        }
    }
}
