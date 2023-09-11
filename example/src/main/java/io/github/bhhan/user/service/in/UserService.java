package io.github.bhhan.user.service.in;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.bhhan.exception.CustomException;
import io.github.bhhan.exception.ErrorCode;
import io.github.bhhan.user.domain.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static io.github.bhhan.user.domain.QUser.user;
import static io.github.bhhan.user.domain.QUserGroup.userGroup;

@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final JPAQueryFactory queryFactory;

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class UserInformation {
        private Long userId;
        private Long groupId;
        private String userName;
        private UserStatus status;
        private String phone;
        private String groupName;
        private String description;
    }

    public void addUser(User user) {
        try {
            userRepository.add(user);
        } catch (Exception e) {
            throw new CustomException("User ID: " + user.getId() + " 추가에 실패했습니다.", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public UserInformation getUserById(Long userId) {
        try {
            return Optional.ofNullable(queryFactory.select(
                                    Projections.fields(UserInformation.class,
                                            user.id.as("userId"),
                                            user.groupId,
                                            user.name.as("userName"),
                                            user.status,
                                            user.phone,
                                            userGroup.name.as("groupName"),
                                            userGroup.description)
                            )
                            .from(user)
                            .join(userGroup)
                            .on(user.id.eq(userGroup.id))
                            .where(user.id.eq(userId))
                            .fetchOne())
                    .orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException ex) {
            throw new CustomException("User ID: " + userId + "를 찾을 수 없습니다.", ErrorCode.BAD_REQUEST);
        } catch (Exception ex) {
            throw new CustomException("User ID: " + userId + "를 찾을 수 없습니다.", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public void addUserGroup(UserGroup userGroup) {
        try {
            userGroupRepository.add(userGroup);
        } catch (Exception e) {
            throw new CustomException("UserGroup ID: " + userGroup.getId() + " 추가에 실패했습니다.", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<User> findByGroupId(Long groupId, Pageable pageable) {
        try {
            return userRepository.findByGroupId(groupId, pageable);
        } catch (Exception e) {
            throw new CustomException("Group ID: " + groupId + " 검색에 실패했습니다.", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteById(Long id) {
        try {
            userRepository.deleteUserById(id);
        } catch (IllegalArgumentException ex) {
            throw new CustomException("User ID: " + id + "를 찾을 수 없습니다.", ErrorCode.BAD_REQUEST);
        } catch (Exception ex) {
            throw new CustomException("User ID: " + id + " 삭제에 실패했습니다.", ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
