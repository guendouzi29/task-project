package com.example.assessment.controller;

import com.example.assessment.entity.Task;
import com.example.assessment.entity.User;
import com.example.assessment.model.TaskDTO;
import com.example.assessment.utils.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.assessment.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    @Autowired
    EmailService emailService;

    @Autowired
    private TaskRepository taskRepository;

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

        //Get all Task
        @GetMapping("/tasks")
        @ApiResponses(value = {
                @ApiResponse(code = 500, message = "Server error"),
                @ApiResponse(code = 404, message = "Service not found"),
                @ApiResponse(code = 200, message = "Successful retrieval",
                        response = Task.class, responseContainer = "List") })
        public List<Task> getAllTasks(){
            return getTaskRepository().findAll();
        }

        //Create new task
        @PostMapping("/tasks")
        public TaskDTO createTask(@ApiParam("taskBody") @Validated @RequestBody TaskDTO taskBody){
            Task task = new Task();
            BeanUtils.copyProperties(taskBody, task);
            getTaskRepository().save(task);
            List<String> userMails = task.getAssignedUsers().stream().map(User::getUserId).collect(Collectors.toList());
            String sub = "task assigned";
            String body = "Task - " + taskBody.getTitle() +  "has been assigned to you with deadline - " + taskBody.getDeadline().toString();
            emailService.sendMail(userMails, sub, body);
            return taskBody;
    }

    @GetMapping("/tasks/{userId}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = TaskDTO.class, responseContainer = "List") })
    public List<TaskDTO> getTasksByUser(@ApiParam(name = "userId") @PathVariable String userId) {
        List<Task> tasks = getTaskRepository().findAll().stream().filter(x ->
                x.getAssignedUsers().stream().map(User::getUserId).collect(Collectors.toList()).contains(userId)).collect(Collectors.toList());
        List<TaskDTO> results = new ArrayList<>();
        if(!CollectionUtils.isEmpty(tasks)) {
            tasks.stream().forEach(x -> {
                TaskDTO temp = new TaskDTO();
                BeanUtils.copyProperties(x, temp);
                results.add(temp);
            });
        }
        return results;
    }
}
