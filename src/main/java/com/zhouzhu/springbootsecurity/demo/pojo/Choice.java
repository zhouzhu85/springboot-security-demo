package com.zhouzhu.springbootsecurity.demo.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author zhouzhu
 * @Description
 * @create 2019-06-13 14:49
 */
@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "poll_id",nullable = false)
    private Poll poll;

    public Choice() {
    }

    public Choice(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)return true;
        if (obj==null || getClass()!=obj.getClass()) return false;
        Choice choice=(Choice)obj;
        return Objects.equals(id,choice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
