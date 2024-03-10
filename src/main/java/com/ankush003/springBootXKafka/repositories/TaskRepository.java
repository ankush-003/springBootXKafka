package com.ankush003.springBootXKafka.repositories;

import com.ankush003.springBootXKafka.domain.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {
    @Query("{ 'userId' : ?0 }")
    TaskEntity findByUserId(String userId);

    @Query("{ 'taskId' : ?0 }")
    TaskEntity findByTaskId(String taskId);
}
