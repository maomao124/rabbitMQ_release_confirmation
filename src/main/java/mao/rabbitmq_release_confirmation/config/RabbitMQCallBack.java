package mao.rabbitmq_release_confirmation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project name(项目名称)：rabbitMQ_release_confirmation
 * Package(包名): mao.rabbitmq_release_confirmation.config
 * Class(类名): RabbitMQCallBack
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 13:04
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Component
public class RabbitMQCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback
{
    private static final Logger log = LoggerFactory.getLogger(RabbitMQCallBack.class);


    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init()
    {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause)
    {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack)
        {
            log.info("消息id为" + id + "的消息交换机已经收到");
        }
        else
        {
            log.warn("交换机未收到id为" + id + "的消息，原因：" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey)
    {
        log.warn("消息不可路由：replyCode:" + replyCode +
                ",replyText:" + replyText + ",exchange:"
                + exchange + ",routingKey:" + routingKey);
    }
}
