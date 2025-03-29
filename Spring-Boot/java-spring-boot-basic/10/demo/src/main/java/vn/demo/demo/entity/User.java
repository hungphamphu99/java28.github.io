package vn.demo.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.demo.demo.model.enums.UserRole;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String username;
    String displayName;

    @Column(unique = true, nullable = false)
    String email;
    String avatar;
    String phone;
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    Boolean isEnabled;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
