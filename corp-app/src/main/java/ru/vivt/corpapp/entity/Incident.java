package ru.vivt.corpapp.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.lang.reflect.Field;

@Table(name = "incident")
@Entity
public class Incident {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reporter_id", nullable = false)
    private Reporter reporter;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Staff assignee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Staff getAssignee() {
        return assignee;
    }

    public void setAssignee(Staff assignee) {
        this.assignee = assignee;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Incident(Long id, String title, Priority priority, Reporter reporter, @Nullable Staff assignee, Status status, String comment) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.reporter = reporter;
        this.assignee = assignee;
        this.status = status;
        this.comment = comment;
    }

    public boolean checkNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
        return true;
    }

    public Incident() {

    }

}