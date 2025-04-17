package vn.demo.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import vn.demo.demo.entity.User;
import vn.demo.demo.model.dto.UserDTO;
import org.mapstruct.Mapping; // ✅ bạn đang thiếu dòng này


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "displayName", target = "displayName")
    })
    UserDTO toDTO(User user);


    @Mappings({
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "displayName", target = "displayName")
    })
    User toEntity(UserDTO dto);
}

