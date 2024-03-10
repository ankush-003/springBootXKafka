package com.ankush003.springBootXKafka.services;

import com.ankush003.springBootXKafka.domain.TaskEvent;

public interface Producer {
    void sendTaskEvent(TaskEvent taskEvent);

//    void sendTaskEvent();
}
