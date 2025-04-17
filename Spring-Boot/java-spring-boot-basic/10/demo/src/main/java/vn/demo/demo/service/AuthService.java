package vn.demo.demo.service;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.User;
import vn.demo.demo.exception.BadRequestException;
import vn.demo.demo.mapper.UserMapper;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.model.request.LoginRequest;
import vn.demo.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;
    private final UserMapper userMapper; // ✅ Thêm dòng này


    public UserDTO login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Tài khoản hoặc mật khẩu không chính xác"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Tài khoản hoặc mật khẩu không chính xác");
        }

        UserDTO dto = userMapper.toDTO(user);
        session.setAttribute("currentUser", dto);
        return dto;
    }


    public void logout() {
        session.removeAttribute("currentUser");
    }
}