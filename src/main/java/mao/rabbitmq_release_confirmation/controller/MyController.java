package mao.rabbitmq_release_confirmation.controller;

import mao.rabbitmq_release_confirmation.config.RabbitMQCallBack;
import mao.rabbitmq_release_confirmation.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Project name(项目名称)：rabbitMQ_release_confirmation
 * Package(包名): mao.rabbitmq_release_confirmation.controller
 * Class(类名): MyController
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/25
 * Time(创建时间)： 13:01
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@RestController
public class MyController
{
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @Autowired
    RabbitTemplate rabbitTemplate;


    @GetMapping("/test1/{message}")
    public String test1(@PathVariable String message)
    {
        log.info("开始发送消息：" + message);
        UUID uuid = UUID.randomUUID();
        rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE_NAME,
                "key2", message, new CorrelationData(uuid.toString()));
        return message;
    }
}

