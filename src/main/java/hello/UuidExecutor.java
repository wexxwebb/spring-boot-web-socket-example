package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UuidExecutor {

    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    @Async
    public void test(List<String> uuids) throws InterruptedException {
        Thread.sleep(2000);
        for (String uuid : uuids) {
            Thread.sleep(100);
            Greeting greeting = new Greeting(uuid);
            messagingTemplate.convertAndSend("/topic/greetings", greeting);
        }
    }
}
