package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.User;
import vn.demo.demo.mapper.UserMapper;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = User.builder()
                .username(userDTO.getUsername())
                .displayName(userDTO.getDisplayName())
                .email(userDTO.getEmail())
                .avatar(userDTO.getAvatar())
                .phone(userDTO.getPhone())
                .password(encodedPassword)
                .role(userDTO.getRole())
                .isEnabled(true)
                .build();

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser); // ✅ sửa lại đúng
    }

    // Get a user by ID
    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user); // ✅
    }

    // Update user details
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setDisplayName(userDTO.getDisplayName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAvatar(userDTO.getAvatar());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRole(userDTO.getRole());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser); // ✅
    }

    // Delete user
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO) // ✅ dùng lambda với instance
                .toList();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUser(Integer id, User userForm) {
        User user = findById(id);
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setRole(userForm.getRole());
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
