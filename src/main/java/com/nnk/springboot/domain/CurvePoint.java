package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "CurveId")
    @NotNull(message = "{not.null}")
    @Positive(message = "{positive}")
    private Integer curveId;

    private Timestamp asOfDate;

    private Double term;

    private Double value;

    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    // CONSTRUCTORS

    public CurvePoint() {}

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    // GETTERS

    public Integer getId() {
        return id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public Double getValue() {
        return value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
