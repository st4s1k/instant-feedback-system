package com.inther;

import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.*")
public class Application extends SpringBootServletInitializer
{
    private final UserRepository userRepository;

    private final ServiceUtilityBean serviceUtilityBean;

    public Application(UserRepository userRepository,
                       ServiceUtilityBean serviceUtilityBean) {
        this.userRepository = userRepository;
        this.serviceUtilityBean = serviceUtilityBean;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generateAdmins() {
        if (!userRepository.findByEmail("admin@isd").isPresent()) {
            userRepository.save(serviceUtilityBean.encodeUserPassword(User.builder()
                    .id(UUID.fromString("c02e5860-342a-11e9-b210-d663bd873d93"))
                    .email("admin@isd")
                    .password("isd228admin69")
                    .role("ADMIN")
                    .build()));
        }
    }
}