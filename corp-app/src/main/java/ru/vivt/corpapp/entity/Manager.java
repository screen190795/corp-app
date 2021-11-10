package ru.vivt.corpapp.entity;

import javax.persistence.*;

@Table(name = "manager")
@Entity
public class Manager {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fio", length = 1)
    private String fio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}