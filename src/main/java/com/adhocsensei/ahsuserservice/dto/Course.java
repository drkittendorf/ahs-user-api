package com.adhocsensei.ahsuserservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String category;

    //    add validation for date format
    private String date;

    private String shortDescription;
    private String location;
    private Integer duration;
    private Integer capacity;
    private String longDescription;


    private Long senseiId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private User user;

    public Course() {
    }

    public Course(Long id, String title, String category, String date, String shortDescription, String location, Integer duration, Integer capacity, String longDescription, Long senseiId, User user) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.shortDescription = shortDescription;
        this.location = location;
        this.duration = duration;
        this.capacity = capacity;
        this.longDescription = longDescription;
        this.senseiId = senseiId;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Long getSenseiId() {
        return senseiId;
    }

    public void setSenseiId(Long senseiId) {
        this.senseiId = senseiId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) && Objects.equals(getTitle(), course.getTitle()) && Objects.equals(getCategory(), course.getCategory()) && Objects.equals(getDate(), course.getDate()) && Objects.equals(getShortDescription(), course.getShortDescription()) && Objects.equals(getLocation(), course.getLocation()) && Objects.equals(getDuration(), course.getDuration()) && Objects.equals(getCapacity(), course.getCapacity()) && Objects.equals(getLongDescription(), course.getLongDescription()) && Objects.equals(getSenseiId(), course.getSenseiId()) && Objects.equals(getUser(), course.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCategory(), getDate(), getShortDescription(), getLocation(), getDuration(), getCapacity(), getLongDescription(), getSenseiId(), getUser());
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", location='" + location + '\'' +
                ", duration=" + duration +
                ", capacity=" + capacity +
                ", longDescription='" + longDescription + '\'' +
                ", senseiId=" + senseiId +
                ", user=" + user +
                '}';
    }
}