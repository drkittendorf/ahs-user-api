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

    @GetMapping("/user/email")
    public User getUserByEmail(@RequestBody User user) {
        return userRepo.findByEmail(user.getEmail());
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        User createdUser = userRepo.save(user);

        Long userIdForAuthorityTable = createdUser.getUserId();
        String userAuthorityForAuthorityTable = createdUser.getAuthority();

        Authority newAuthority = new Authority();
        newAuthority.setUserId(userIdForAuthorityTable);
        newAuthority.setAuthority(userAuthorityForAuthorityTable);

        authorityRepo.save(newAuthority);

        return createdUser;
    }

    @PostMapping("/senseidash/{id}")
    public void addCourseToListOfSenseisCourses(@PathVariable Long id, @RequestBody Course senseiCourse) {
        Long senseiIdFromRequest = senseiCourse.getSenseiId();
        User sensei = userRepo.getById(senseiIdFromRequest);
        Course senseisCreatedCourseFromRepo = courseRepo.getById(senseiCourse.getCourseId());

        sensei.addCourseToSenseiListOfCreatedCourses(senseisCreatedCourseFromRepo);

        userRepo.save(sensei);
    }

    @DeleteMapping("/senseidash/{id}")
    public void removeACourseFromSenseiListOfCourses(@PathVariable Long id, @RequestBody Course senseiCourse) {
        Long senseiIdFromRequest = senseiCourse.getSenseiId();
        User sensei = userRepo.getById(senseiIdFromRequest);
        Course senseisCourseToBeDeletedFromRepo = courseRepo.getById(senseiCourse.getCourseId());

        sensei.removeCourseFromSenseiListOfCreatedCourses(senseisCourseToBeDeletedFromRepo);

        userRepo.save(sensei);
    }

    @PostMapping("/studentdash/{id}")
    public void addCourseToListOfRegisteredCourses(@PathVariable Long id, @RequestBody Course studentCourse) {
        User student = userRepo.getById(id);
        Course studentsAddedCourseFromRepo = courseRepo.getById(studentCourse.getCourseId());

        student.addCourseToStudentListOfRegisteredCourses(studentsAddedCourseFromRepo);

        userRepo.save(student);
    }

    @DeleteMapping("/studentdash/{id}")
    public void unregisterFromCourse(@PathVariable Long id, @RequestBody Course studentCourse) {
        User student = userRepo.getById(id);
        Course courseToDeleteFromStudentRepo = courseRepo.getById(studentCourse.getCourseId());

        student.removeCourseFromStudentListOfRegisteredCourses(courseToDeleteFromStudentRepo);

        userRepo.save(student);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
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