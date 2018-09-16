package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class GreetingController {

    @Autowired
    private UuidExecutor uuidExecutor;

    private List<String> uuids;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard() throws InterruptedException {
        ModelAndView modelAndView = new ModelAndView("/dashboard");
        uuids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            uuids.add(UUID.randomUUID().toString());
        }
        modelAndView.addObject("uuids", uuids);
        return modelAndView;
    }

    @RequestMapping("/getresults")
    @ResponseBody
    public void getResults() throws InterruptedException {
        uuidExecutor.test(uuids);
    }

}
