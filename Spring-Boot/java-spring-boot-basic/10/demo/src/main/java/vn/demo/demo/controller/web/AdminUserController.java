package vn.demo.demo.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.User;
import vn.demo.demo.model.enums.UserRole;
import vn.demo.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    // Hiển thị danh sách người dùng
    @GetMapping
    public String getUserList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    // Hiển thị form chỉnh sửa người dùng
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        return "admin/user-edit";
    }

    // Xử lý form cập nhật người dùng
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute User userForm) {
        userService.updateUser(id, userForm);
        return "redirect:/admin/users";
    }

    // Xóa người dùng
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}