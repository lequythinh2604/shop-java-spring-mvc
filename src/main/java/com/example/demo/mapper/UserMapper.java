package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.util.UserCustomMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserCustomMapper.class})
public interface UserMapper {

    @Mapping(source = "dto", target = "fullName", qualifiedByName = "toFullName")
    User toUser(RegisterDTO dto);
}
