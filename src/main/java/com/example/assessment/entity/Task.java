package com.example.assessment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Task {

    @Id
    private Integer id;
    private String title;
    @ManyToMany
    private Collection<User> assignedUsers;
    private LocalDateTime deadline;

    public Task(){

    }
    public Task(String title, List<User> assignedUsers, LocalDateTime deadline){
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
