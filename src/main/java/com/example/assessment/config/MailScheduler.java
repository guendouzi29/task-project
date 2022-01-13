package com.example.assessment.config;

import com.example.assessment.entity.Task;
import com.example.assessment.entity.User;
import com.example.assessment.repository.TaskRepository;
import com.example.assessment.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MailScheduler {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "* */5 * * * *")
    public void sendScheduledMail() {
        List<Task> tasks = taskRepository.findForDeadline(LocalDateTime.now());
        String sub = "task deadline passed";
        String body = "Task deadline has passed";
        if(!CollectionUtils.isEmpty(tasks)) {
            tasks.stream().forEach(x -> emailService.sendMail(x.getAssignedUsers().stream().map(User::getUserId).collect(Collectors.toList()), sub, body));
        }
    }


}
