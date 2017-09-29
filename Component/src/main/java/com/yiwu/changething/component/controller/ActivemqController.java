package com.yiwu.changething.component.controller;

import javax.annotation.Resource;

import com.yiwu.changething.component.mq.queue.QueueSender;
import com.yiwu.changething.component.mq.topic.TopicSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author liang
 * @description controller测试
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {

    @Resource
    private QueueSender queueSender;

    @Resource
    private TopicSender topicSender;


    /**
     * todo 开启activeMQ服务
     * 发送消息到队列
     * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
     * @param message
     * @return String
     */
    @ResponseBody
    @PostMapping("/queueSender")
    public String queueSender(@RequestParam("message") String message) {
        String opt = "";
        try {
            queueSender.send("test.queue", message);
            opt = "success";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }

    /**
     * 发送消息到主题
     * Topic主题 ：放入一个消息，所有订阅者都会收到
     * 这个是主题目的地是一对多的
     *
     * @param message
     * @return String
     */
    @ResponseBody
    @PostMapping("/topicSender")
    public String topicSender(@RequestParam("message") String message) {
        String opt = "";
        try {
            topicSender.send("test.topic", message);
            opt = "success";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }

}
