package mao.rabbitmq_release_confirmation.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project name(项目名称)：rabbitMQ_release_confirmation
 * Package(包名): mao.rabbitmq_release_confirmation.config
 * Class(类名): RabbitMQConfig
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 12:53
 * Version(版本): 1.0
 * Description(描述)： 无
 */


@Configuration
public class RabbitMQConfig
{
    /**
     * The constant CONFIRM_EXCHANGE_NAME.
     */
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    /**
     * The constant CONFIRM_QUEUE_NAME.
     */
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";

    /**
     * Gets message converter.
     *
     * @return the message converter
     */
    @Bean
    public MessageConverter getMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Confirm exchange direct exchange.
     *
     * @return the direct exchange
     */
    @Bean
    public DirectExchange confirm_exchange()
    {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME, false, false, null);
    }

    /**
     * Confirm queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue confirm_queue()
    {
        return new Queue(CONFIRM_QUEUE_NAME, false, false, false, null);
    }

    /**
     * Confirm exchange bind confirm queue binding.
     *
     * @param confirm_exchange the confirm exchange
     * @param confirm_queue    the confirm queue
     * @return the binding
     */
    @Bean
    public Binding confirm_exchange_bind_confirm_queue(@Qualifier("confirm_exchange") DirectExchange confirm_exchange,
                                                       @Qualifier("confirm_queue") Queue confirm_queue)
    {
        return BindingBuilder.bind(confirm_queue).to(confirm_exchange).with("key1");
    }

}
