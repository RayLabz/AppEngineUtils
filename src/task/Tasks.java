package task;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

public class Tasks {

    public static Queue withQueue(String queueName) {
        return QueueFactory.getQueue(queueName);
    }

    public static Queue withDefaultQueue() {
        return QueueFactory.getDefaultQueue();
    }

}
