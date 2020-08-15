package main.java;

public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markAsCompleted(){
        this.isCompleted = true;
    }

    public String getState() {
        if(this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getState() + "] " + description;
    }

}
