package com.web.roomiez.Task;
//THIS CLASS INTERACTS WITH THE DATABASE
import com.web.roomiez.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Query(value = "UPDATE TaskTable t SET t.progress = ?2 WHERE t.taskID = ?1", nativeQuery = true)
    void updateTaskProgress(int id, int progress);

    @Modifying
    @Query(value = "UPDATE TaskTable t SET t.endDate = ?2 WHERE t.taskID = ?1", nativeQuery = true)
    void updateTaskEndDate(@Param("id") int id,  @Param("endDate") String endDate);

    @Modifying
    @Query(value = "UPDATE TASK t SET t.endTime = ?2 WHERE t.taskID = ?1", nativeQuery = true)
    void updateTaskEndTime(@Param("id") int id,  @Param("endTime") String endTime);

    //Query to get group ID associated with the user
    @Query("SELECT u.groupID FROM User u WHERE u.username = ?1")
    int getGroupIDWithUsername(@Param("username") String username);

    //Get task repeat with taskID
//    @Query("SELECT t.repeatTask FROM TaskTable t WHERE t.taskID = ?1")
//    String getTaskRepeatWithTaskID(@Param("taskID") int taskID);

//    @Query("SELECT t FROM TaskTable t WHERE t.groupID = ?1")
//    List<Task> findGroupTasks(@Param("groupID") int groupID);

}
