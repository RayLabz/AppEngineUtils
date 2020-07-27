package com.raylabz.appengineutils.task;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class Tasks {

    public static void runInBackground(TaskOptions options) {
        QueueFactory.getDefaultQueue().add(options);
    }

    public static void runInBackground(String queueName, TaskOptions options) throws NoQueueException {
        Queue queue = QueueFactory.getQueue(queueName);
        if (queue != null) {
            queue.add(options);
        }
        throw new NoQueueException("The queue '" + queueName + "' does not exist - the task will not be executed.");
    }

}
