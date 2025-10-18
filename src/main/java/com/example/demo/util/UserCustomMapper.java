package com.example.demo.util;

import com.example.demo.domain.dto.RegisterDTO;
import org.mapstruct.Named;

public class UserCustomMapper {

    @Named("toFullName")
    public static String toFullName(RegisterDTO dto) {
        StringBuilder fullName = new StringBuilder();
        if (dto.getFirstName() != null) {
            fullName.append(dto.getFirstName().trim());
        }
        if (dto.getLastName() != null) {
            if (fullName.length() > 0) {
                fullName.append(" ");
            }
            fullName.append(dto.getLastName().trim());
        }
        return fullName.toString();
    }
}
