package io.github.bhhan.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.bhhan.user.domain.UserGroupRepository;
import io.github.bhhan.user.domain.UserRepository;
import io.github.bhhan.user.service.in.UserService;
import io.github.bhhan.user.service.out.UserGroupRepositoryImpl;
import io.github.bhhan.user.service.out.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;

public class UserConfig {
    @Bean
    public UserRepository userRepository(JPAQueryFactory jpaQueryFactory) {
        return new UserRepositoryImpl(jpaQueryFactory);
    }

    @Bean
    public UserGroupRepository userGroupRepository() {
        return new UserGroupRepositoryImpl();
    }

    @Bean
    public UserService userService(UserRepository userRepository, UserGroupRepository userGroupRepository,
                                   JPAQueryFactory queryFactory) {
        return new UserService(userRepository, userGroupRepository, queryFactory);
    }
}
