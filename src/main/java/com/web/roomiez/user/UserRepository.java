package com.web.roomiez.user;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //returns the user object based on username
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User a " + "SET a.enabled = TRUE WHERE a.username = ?1", nativeQuery = true)
    int enableUser(String username);

    // grabbing users assigned to group with groupID
    @Query("SELECT u FROM User u WHERE u.groupID = ?1")
    List<User> findUsersInGroup(@Param("groupID") int groupID);
}


