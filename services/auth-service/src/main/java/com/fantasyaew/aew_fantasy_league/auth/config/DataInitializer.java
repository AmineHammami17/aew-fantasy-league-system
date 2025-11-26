package com.fantasyaew.aew_fantasy_league.auth.config;

import com.fantasyaew.aew_fantasy_league.auth.entity.Role;
import com.fantasyaew.aew_fantasy_league.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        initializeRoles();
    }

    private void initializeRoles() {
        if (roleRepository.findByName(Role.RoleType.USER).isEmpty()) {
            Role userRole = Role.builder()
                    .name(Role.RoleType.USER)
                    .description("Regular user role")
                    .build();
            roleRepository.save(userRole);
            log.info("Created USER role");
        }

        if (roleRepository.findByName(Role.RoleType.ADMIN).isEmpty()) {
            Role adminRole = Role.builder()
                    .name(Role.RoleType.ADMIN)
                    .description("Administrator role")
                    .build();
            roleRepository.save(adminRole);
            log.info("Created ADMIN role");
        }
    }
}
