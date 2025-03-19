package mthree.com.fullstackschool.controller;

import mthree.com.fullstackschool.model.Teacher;
import mthree.com.fullstackschool.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherServiceImpl.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        Teacher result = teacherServiceImpl.getTeacherById(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found at id: " + id);
        }
        return result;

        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        //YOUR CODE STARTS HERE

        return teacherServiceImpl.addNewTeacher(teacher);

        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        //YOUR CODE STARTS HERE
        if (id != teacher.getTeacherId()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ID in path does not match ID in request body");
        }

        Teacher updatedTeacher = teacherServiceImpl.updateTeacherData(id, teacher);
        if (updatedTeacher == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found at id: " + id);
        }
        return updatedTeacher;

        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        //YOUR CODE STARTS HERE

        teacherServiceImpl.deleteTeacherById(id);

        //YOUR CODE ENDS HERE
    }
}
