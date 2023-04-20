package com.web.roomiez;

import com.web.roomiez.Task.Progress;
import com.web.roomiez.Task.Task;
import com.web.roomiez.Task.TaskRepository;
import com.web.roomiez.group.Group;
import com.web.roomiez.group.GroupRepository;
import com.web.roomiez.user.User;
import com.web.roomiez.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

// DEPENDENCY INJECTION + BEAN INSTANTIATION
@Configuration
public class ApplicationConfig {

    // populating with some example groups
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, GroupRepository groupRepository, TaskRepository taskRepository) {
        return args -> {
            Group g1 = new Group(
                    1,
                    "Dream Team"
            );

            Group g2 = new Group(
                    2,
                    "Papa"
            );

            Group g3 = new Group(
                    "USC"
            );

            groupRepository.saveAll(
                    List.of(g1, g2, g3)
            );

            User u1 = new User();
            u1.setGroupID(3);
            u1.setName("Kory");
            u1.setUsername("arfania@usc.edu");
            u1.setPassword("123");

            User u2 = new User();
            u2.setGroupID(2);
            u2.setName("Chely");
            u2.setUsername("chely@usc.edu");
            u2.setPassword("1234");

            User u3 = new User();
            u1.setGroupID(1);
            u1.setName("Tyler");
            u1.setUsername("tyler@usc.edu");
            u1.setPassword("12345");

            userRepository.saveAll(
                    List.of(u1, u2, u3)
            );

            Task t1 = new Task(
                    1,
                    1,
                    "Bob",
                    3,
                    "Task 1",
                    "01/01/1970",
                    "01/02/1970",
                    "2:00pm",
                    "5:00pm",
                    0,
                    "This is a dummy task.",
                    "weekly"
            );

            taskRepository.save(t1);
        };
    }
}
