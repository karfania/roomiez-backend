package com.web.roomiez.group;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// DEPENDENCY INJECTION + BEAN INSTANTIATION
@Configuration
public class GroupConfig {

    // populating with some example groups
    @Bean
    CommandLineRunner commandLineRunner(GroupRepository groupRepository) {
        return args -> {
            Group g1 = new Group(
                    1,
                    "Dream Team"
            );

            Group g2 = new Group(
                    2,
                    "Papa"
            );

            groupRepository.saveAll(
                    List.of(g1, g2)
            );
        };
    }
}
