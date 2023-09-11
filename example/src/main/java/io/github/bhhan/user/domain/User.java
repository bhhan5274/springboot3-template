package io.github.bhhan.user.domain;

import io.github.bhhan.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", indexes = {
        @Index(columnList = "groupId", name = "idx_group_id")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private Long groupId;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false)
    private String phone;

    @Builder
    public User(Long id, Long groupId, String name, UserStatus status, String phone) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.status = status;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                '}';
    }
}
