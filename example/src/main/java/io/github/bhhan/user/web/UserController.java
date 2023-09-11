package io.github.bhhan.user.web;

import io.github.bhhan.user.domain.User;
import io.github.bhhan.user.service.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Page<User> findUsersByGroupId(@RequestParam("groupId") Long groupId, Pageable pageable) {
        return userService.findByGroupId(groupId, pageable);
    }

    @GetMapping("/{userId}")
    public UserService.UserInformation getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
