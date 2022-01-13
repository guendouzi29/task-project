package com.example.assessment.model;


import com.example.assessment.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Component
public class TaskDTO {

    private String title;
    private Collection<User> assignedUsers;
    private LocalDateTime deadline;

    public TaskDTO(){

    }
    public TaskDTO(String title, List<User> assignedUsers, LocalDateTime deadline){
        this.title = title;
        this.assignedUsers = assignedUsers;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
