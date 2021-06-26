package com.adhocsensei.ahsuserservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String authority;
    private String firstName;
    private String lastName;
//    validation for no repeats
    private String email;
    private String password;
    private String bio;
    private boolean instructor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Course> senseisCreatedCourses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Course> studentsRegisteredCourses;

    public User() {
    }

    public User(Long userId, String authority, String firstName, String lastName, String email, String password, String bio, boolean instructor, Set<Course> senseisCreatedCourses, Set<Course> studentsRegisteredCourses) {
        this.userId = userId;
        this.authority = authority;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.instructor = instructor;
        this.senseisCreatedCourses = senseisCreatedCourses;
        this.studentsRegisteredCourses = studentsRegisteredCourses;
    }
//  this constructor is used for testing controller without having senseis courses or students courses
    public User(Long userId, String authority, String firstName, String lastName, String email, String password, String bio, boolean instructor) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

    public Set<Course> getSenseisCreatedCourses() {
        return senseisCreatedCourses;
    }

    public void setSenseisCreatedCourses(Set<Course> senseisCreatedCourses) {
        this.senseisCreatedCourses = senseisCreatedCourses;
    }

    public Set<Course> getStudentsRegisteredCourses() {
        return studentsRegisteredCourses;
    }

    public void setStudentsRegisteredCourses(Set<Course> studentsRegisteredCourses) {
        this.studentsRegisteredCourses = studentsRegisteredCourses;
    }

    public void addCourseToSenseiListOfCreatedCourses(Course courseId) {
        senseisCreatedCourses.add(courseId);
    }

    public void addCourseToStudentListOfRegisteredCourses(Course courseId) {
        studentsRegisteredCourses.add(courseId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isInstructor() == user.isInstructor() && Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getAuthority(), user.getAuthority()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getBio(), user.getBio()) && Objects.equals(getSenseisCreatedCourses(), user.getSenseisCreatedCourses()) && Objects.equals(getStudentsRegisteredCourses(), user.getStudentsRegisteredCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getAuthority(), getFirstName(), getLastName(), getEmail(), getPassword(), getBio(), isInstructor(), getSenseisCreatedCourses(), getStudentsRegisteredCourses());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", authority='" + authority + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", instructor=" + instructor +
                ", senseisCreatedCourses=" + senseisCreatedCourses +
                ", studentsRegisteredCourses=" + studentsRegisteredCourses +
                '}';
    }
}