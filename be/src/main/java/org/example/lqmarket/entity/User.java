package org.example.lqmarket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.lqmarket.enums.AuthProvider;
import org.example.lqmarket.enums.Role;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(unique = true, nullable = false)
    String email;

    String password;
    String fullName;
    String address;
    String phone;
    String avatar;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    AuthProvider authProvider;

    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    LocalDateTime updatedAt = LocalDateTime.now();

    @Builder.Default
    Boolean active = true;
}
