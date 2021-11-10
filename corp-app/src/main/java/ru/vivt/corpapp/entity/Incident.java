package ru.vivt.corpapp.entity;

import javax.persistence.*;

@Table(name = "incident")
@Entity
public class Incident {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}