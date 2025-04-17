package vn.demo.demo.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.demo.demo.model.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Integer id;
    String username;
    String displayName;
    String email;
    String avatar;
    String phone;
    UserRole role;
    String password;
}
