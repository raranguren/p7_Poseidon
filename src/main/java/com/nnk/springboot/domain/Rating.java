package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "MoodysRating is mandatory")
    private String moodysRating;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "SandPRating is mandatory")
    private String sandPRating;

    @Size(max = 125, message = "Maximum of {max} characters")
    @NotBlank(message = "FitchRating is mandatory")
    private String fitchRating;

    @NotNull(message = "An order number is mandatory")
    private Integer orderNumber;

    // CONSTRUCTORS

    public Rating() {}

    public Rating(String moodysRating, String sandPRating, String fitchRating, int orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    // GETTER

    public Integer getId() {
        return id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    // SETTER

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
