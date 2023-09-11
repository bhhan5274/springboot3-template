package io.github.bhhan.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String description;

    @Builder
    public UserGroup(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
