package io.github.bhhan;

import io.github.bhhan.config.QuerydslConfig;
import io.github.bhhan.user.UserConfig;
import io.github.bhhan.user.service.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@Import({
        QuerydslConfig.class,
        UserConfig.class
})
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        /*IntStream.rangeClosed(1, 3)
                .boxed()
                .forEach(i -> userService.addUserGroup(UserGroup.builder()
                        .name("Group" + i)
                        .description("UserGroup: " + i)
                        .build()));

        IntStream.rangeClosed(1, 300)
                .boxed()
                .forEach(i -> userService.addUser(User.builder()
                        .name("bhhan" + i)
                        .phone("phone")
                        .status(UserStatus.ACTIVE)
                        .groupId(((long) (i % 3)) + 1)
                        .build()));*/
    }
}
