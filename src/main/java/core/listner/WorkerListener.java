package core.listner;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class WorkerListener {
    private final Logger logger = Logger.getLogger(WorkerListener.class);

    @RabbitListener(queues = "queue")
    public void processQueue1(String message) {
        logger.info("Received from queue : " + message);
    }

    @RabbitListener(queues = "testQueue1")
    public void worker1(String message) throws InterruptedException {
        logger.info("testQueue1 work  " + message);
        Thread.sleep(100);
    }

    @RabbitListener(queues = "testQueue2")
    public void worker2(String message) throws InterruptedException {
        logger.info("testQueue2 work " + message);
        Thread.sleep(100);
    }
}