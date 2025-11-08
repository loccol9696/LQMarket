package org.example.lqmarket.service.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.lqmarket.dto.request.RegisterRequest;
import org.example.lqmarket.entity.User;
import org.example.lqmarket.enums.AuthProvider;
import org.example.lqmarket.exception.BusinessException;
import org.example.lqmarket.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Đăng ký tài khoản mới bằng email và password.
     */
    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email đã tồn tại trong hệ thống", 400);
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessException("Mật khẩu xác nhận không khớp", 400);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .authProvider(AuthProvider.NONE)
                .build();

        userRepository.save(user);
    }
}
