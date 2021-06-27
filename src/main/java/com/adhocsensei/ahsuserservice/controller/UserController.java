package com.adhocsensei.ahsuserservice.controller;

import com.adhocsensei.ahsuserservice.dao.AuthorityRepository;
import com.adhocsensei.ahsuserservice.dao.CourseRepository;
import com.adhocsensei.ahsuserservice.dao.UserRepository;
import com.adhocsensei.ahsuserservice.dto.Authority;
import com.adhocsensei.ahsuserservice.dto.Course;
import com.adhocsensei.ahsuserservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    AuthorityRepository authorityRepo;

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepo.findById(id);
    }

//    @GetMapping("/user/email")
//    public User getUserByEmail(@RequestBody User user) {
//        return userRepo.findByEmail(user.getEmail());
//    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        User createdUser = userRepo.save(user);

        Long userIdForAuthorityTable = createdUser.getId();
        String userAuthorityForAuthorityTable = createdUser.getAuthority();

        Authority newAuthority = new Authority();
        newAuthority.setUserId(userIdForAuthorityTable);
        newAuthority.setAuthority(userAuthorityForAuthorityTable);

        authorityRepo.save(newAuthority);

        return createdUser;
    }

    @PostMapping("/studentdash/{id}")
    public void registerForCourse(@PathVariable Long id, @RequestBody Course studentCourse) {
        User student = userRepo.getById(id);

        student.getStudentsRegisteredCourses().add(studentCourse);

        userRepo.save(student);
    }

    @DeleteMapping("/studentdash/{id}")
    public void unregisterFromCourse(@PathVariable Long id, @RequestBody Course studentCourse) {
        User student = userRepo.getById(id);
        Course courseToDeleteFromStudentRepo = courseRepo.getById(studentCourse.getId());

        student.removeCourseFromStudentListOfRegisteredCourses(courseToDeleteFromStudentRepo);

        userRepo.save(student);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userRepo.save(user);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User loginUser(@RequestBody User user) throws Exception {
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));

        if (optionalUser.isPresent()) {
            userRepo.findByEmail(user.getEmail());
//
            if (user.getPassword().equals(userRepo.findByEmail(user.getEmail()).getPassword())) {
                return userRepo.findByEmail(user.getEmail());
            }
////            add exception handling to return statement saying username or password is incorrect
//            return "Incorrect username or password";
            return null;
//            throw new Exception();
        }
////        add exception handling to return statement saying username or password is incorrect
//        return "Incorrect username or password";
        return null;
//        throw new Exception();
    }
}