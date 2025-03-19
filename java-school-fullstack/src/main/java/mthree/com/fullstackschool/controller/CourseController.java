package mthree.com.fullstackschool.controller;

import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpUtils;
import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseService.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        Course result = courseService.getCourseById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id);
        }
        return result;

        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        //YOUR CODE STARTS HERE

        return courseService.addNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        //YOUR CODE STARTS HERE
        if (id != course.getCourseId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ID in path does not match ID in request body");
        }

        Course updatedCourse = courseService.updateCourseData(id, course);
        if (updatedCourse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with id: " + id);
        }

        return updatedCourse;

        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        courseService.deleteCourseById(id);

        //YOUR CODE ENDS HERE
    }
}
