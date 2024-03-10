package com.ankush003.springBootXKafka.services;

import com.ankush003.springBootXKafka.domain.TaskEntity;

public interface Producer {
    void produceTaskEntity(TaskEntity taskEntity);

//    void sendTaskEvent();
}
