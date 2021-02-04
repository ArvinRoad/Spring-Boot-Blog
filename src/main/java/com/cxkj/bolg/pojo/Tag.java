package com.cxkj.bolg.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Bolg> bolgs = new ArrayList<>();

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bolg> getBolgs() {
        return bolgs;
    }

    public void setBolgs(List<Bolg> bolgs) {
        this.bolgs = bolgs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
