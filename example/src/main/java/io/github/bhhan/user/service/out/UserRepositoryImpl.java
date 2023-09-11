package io.github.bhhan.user.service.out;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.bhhan.user.domain.User;
import io.github.bhhan.user.domain.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static io.github.bhhan.user.domain.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Override
    public User add(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public boolean exist(Long userId) {
        Integer fetchOne = queryFactory.selectOne()
                .from(user)
                .where(user.id.eq(userId))
                .fetchFirst();
        return fetchOne != null;
    }

    @Override
    public User findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserId: " + id));
    }

    @Override
    public void deleteUserById(Long id) {
        User findUser = findById(id);
        entityManager.remove(findUser);
    }

    @Override
    public Page<User> findByGroupId(Long groupId, Pageable pageable) {
        List<User> users = queryFactory.selectFrom(user)
                .where(user.groupId.eq(groupId))
                .orderBy(user.id.asc())
                .offset((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(user.groupId.count())
                .from(user)
                .where(user.groupId.eq(groupId));

        return PageableExecutionUtils.getPage(users, pageable, countQuery::fetchOne);
    }
}
