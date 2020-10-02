package core.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private final Logger logger = Logger.getLogger(SampleController.class);
    private final RabbitTemplate template;

    @Autowired
    public SampleController(final RabbitTemplate template) {
        this.template = template;
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Empty mapping";
    }

    @RequestMapping(value = "/emit", method = GET)
    public String emit() {
        logger.info("Emit to exchange-example-3");
        template.setExchange("exchange-example-3");
        template.convertAndSend("Fanout message");
        return "Emit to exchange-example-3";
    }
}