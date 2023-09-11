package io.github.bhhan.user.service.out;

import io.github.bhhan.user.domain.UserGroup;
import io.github.bhhan.user.domain.UserGroupRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserGroupRepositoryImpl implements UserGroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserGroup add(UserGroup userGroup) {
        entityManager.persist(userGroup);
        return userGroup;
    }

}
