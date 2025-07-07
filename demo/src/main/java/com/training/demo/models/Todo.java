package com.training.demo.models;

import java.time.LocalDateTime;

public class Todo {
    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    public Todo() {}

    public Todo(Long id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }
    public Long getId(){ return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String t){
        this.title = t;
    }

    public boolean isCompleted(){
        return completed;
    }
    public void setCompleted(boolean c) {
        this.completed = c;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime ts){
        this.createdAt = ts;
    }
}
