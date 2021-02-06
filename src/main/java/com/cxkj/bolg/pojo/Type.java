package com.cxkj.bolg.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by Arvin on 2021/2/4.
 */
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "程序员大大，分类名称不能为空哦Σ( °△°|||)")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Bolg> bolgs = new ArrayList<>();

    public Type() {
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
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
