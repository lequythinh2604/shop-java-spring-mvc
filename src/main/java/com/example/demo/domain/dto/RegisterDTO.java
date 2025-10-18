package com.example.demo.domain.dto;

import com.example.demo.service.validator.define.RegisterChecked;
import com.example.demo.service.validator.define.StrongPwd;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@RegisterChecked
public class RegisterDTO {

    @Size(min = 3, message = "FirstName phải có tối thiểu 3 ký tự")
    private String firstName;

    private String lastName;

    @Email(message = "Email không hợp lệ")
    private String email;

    @StrongPwd(message = "Password phải chứa ít nhất 8 kí tự và kết hợp chữ hoa, chữ thường, số, kí tự đặc biệt")
    private String password;

    private String confirmPassword;
}
