package com.example.demo.service.validator;

import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.service.UserService;
import com.example.demo.service.validator.define.RegisterChecked;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(RegisterDTO userDTO, ConstraintValidatorContext context) {

        boolean valid = true;

        // Check if password fields match
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Password nhập không chính xác")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        // check email
        if (this.userService.checkEmailExist(userDTO.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
