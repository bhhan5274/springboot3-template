package io.github.bhhan.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
    User add(User user);

    boolean exist(Long userId);

    User findById(Long id);
    void deleteUserById(Long id);

    Page<User> findByGroupId(Long groupId, Pageable pageable);
}
