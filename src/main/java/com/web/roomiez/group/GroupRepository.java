package com.web.roomiez.group;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// DATA ACCESS LAYER
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    // querying database to get group via groupID
    @Query("SELECT g FROM Group g WHERE g.groupID = ?1")
    Optional<Group> findGroupByID(@Param("groupID") int groupID);

    // querying database to get groups via groupID
    @Query("SELECT g FROM Group g WHERE g.groupName = ?1")
    List<Group> findGroupsByName(@Param("groupName") String groupName);

    // modifying group name
    @Modifying
    @Query("UPDATE GROUP g SET g.groupName = ?2 WHERE g.groupID = ?1")
    void updateGroupNameByID(int groupID, String groupName);
}