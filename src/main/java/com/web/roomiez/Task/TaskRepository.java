package com.web.roomiez.Task;
//THIS CLASS INTERACTS WITH THE DATABASE
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Query("UPDATE Task t SET t.progress = :progress WHERE t.id = :id")
    void updateTaskProgress(@Param("id") int id,  @Param("progress") int progress);

    @Modifying
    @Query("UPDATE TASK t SET t.endDate = :endDate WHERE t.id = :id")
    void updateTaskEndDate(@Param("id") int id,  @Param("endDate") String endDate);

    @Modifying
    @Query("UPDATE TASK t SET t.endTime = :endTime WHERE t.id = :id")
    void updateTaskEndTime(@Param("id") int id,  @Param("endTime") String endTime);

}
