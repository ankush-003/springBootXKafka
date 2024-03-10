package com.ankush003.springBootXKafka.repositories;

import com.ankush003.springBootXKafka.domain.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {
    @Query("{ 'userId' : ?0 }")
    List<TaskEntity> findByUserId(String userId);

    @Query("{ 'taskId' : ?0 }")
    Optional<TaskEntity> findByTaskId(String taskId);
}
