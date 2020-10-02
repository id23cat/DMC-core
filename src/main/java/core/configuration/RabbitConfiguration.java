package core.configuration;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import static org.springframework.amqp.core.BindingBuilder.bind;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://localhost:8080/emit
@Configuration
public class RabbitConfiguration {
    private final Logger logger = Logger.getLogger(RabbitConfiguration.class);
    private static final String CONNECTION_NAME = "localhost";
    private static final String INTERCEPTOR_MSG = "received from queue : ";
    private static final String QUEUE_NAME = "queue";


    /**
     * Bean for connection with RabbitMQ.
     *
     * @return new connection.
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(CONNECTION_NAME);
    }

    /**
     * Bean for register/delete queue.
     *
     * @return new admin.
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    /**
     * producer
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    // When we can real test rabbit mq please remove

    @Bean
    public FanoutExchange fanoutExchangeA() {
        return new FanoutExchange("exchange-example-3");
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Queue testQueue1() {
        return new Queue("testQueue1");
    }

    @Bean
    public Queue testQueue2() {
        return new Queue("testQueue2");
    }

    @Bean
    public Binding binding1() {
        return bind(testQueue1()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2() {
        return bind(testQueue2()).to(fanoutExchangeA());
    }
}