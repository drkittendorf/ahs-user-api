package com.adhocsensei.ahsuserservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authority;
    private String firstName;
    private String lastName;
//    validation for no repeats
    private String email;
    private String password;
    private String bio;
    private boolean instructor;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Course> senseisCreatedCourses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Course> studentsRegisteredCourses = new ArrayList<>();

    public User() {
    }

    public User(Long id, String authority, String firstName, String lastName, String email, String password, String bio, boolean instructor, List<Course> studentsRegisteredCourses) {
        this.id = id;
        this.authority = authority;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.instructor = instructor;
        this.studentsRegisteredCourses = studentsRegisteredCourses;
    }

    //  this constructor is used for testing controller without having senseis courses or students courses
    public User(Long id, String authority, String firstName, String lastName, String email, String password, String bio, boolean instructor) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Course> getStudentsRegisteredCourses() {
        return studentsRegisteredCourses;
    }

    public void setStudentsRegisteredCourses(List<Course> studentsRegisteredCourses) {
        this.studentsRegisteredCourses = studentsRegisteredCourses;
    }

    public void addCourseToStudentListOfRegisteredCourses(Course courseId) {
        studentsRegisteredCourses.add(courseId);
    }

    public void removeCourseFromStudentListOfRegisteredCourses(Course courseId) {
        studentsRegisteredCourses.remove(courseId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isInstructor() == user.isInstructor() && Objects.equals(getId(), user.getId()) && Objects.equals(getAuthority(), user.getAuthority()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getBio(), user.getBio()) && Objects.equals(getStudentsRegisteredCourses(), user.getStudentsRegisteredCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthority(), getFirstName(), getLastName(), getEmail(), getPassword(), getBio(), isInstructor(), getStudentsRegisteredCourses());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", instructor=" + instructor +
                ", studentsRegisteredCourses=" + studentsRegisteredCourses +
                '}';
    }

}