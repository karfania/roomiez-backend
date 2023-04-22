package com.web.roomiez.Task;
//THIS CLASS INTERACTS WITH THE DATABASE

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

    List<Task> findByGroup_groupID(int groupID);
    List<Task> findByUser_ID(int userID);

}
