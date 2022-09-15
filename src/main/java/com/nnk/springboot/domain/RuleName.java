package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "json is mandatory")
    private String json;

    @Size(max = 512, message = "Maximum of {max} characters")
    @NotBlank(message = "template is mandatory")
    private String template;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "sql is mandatory")
    private String sqlStr;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "sqlPart is mandatory")
    private String sqlPart;

    //CONSTRUCTORS

    public RuleName() {}

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    // GETTER

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getJson() {
        return json;
    }

    public String getTemplate() {
        return template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    // SETTER

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
