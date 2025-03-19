package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.dao.mappers.StudentMapper;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    private final JdbcTemplate jdbcTemplate;
    private final StudentDao studentDao;
    private final CourseDao courseDao;

    public StudentServiceImpl(JdbcTemplate jdbcTemplate, StudentDao studentDao, CourseDao courseDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE
        try {
            return studentDao.findStudentById(id);
        } catch (DataAccessException ex) {
            Student studentNotFound = new Student();
            studentNotFound.setStudentFirstName("Student NOT Found");
            studentNotFound.setStudentLastName("Student NOT Found");
            return studentNotFound;
        }
        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        if (student.getStudentFirstName() == null || student.getStudentFirstName().trim().isEmpty() ||
            student.getStudentLastName() == null || student.getStudentLastName().trim().isEmpty()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            student.setStudentLastName("Last Name blank, student NOT added");
            return student;
        }
        return studentDao.createNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if ( id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        }

        studentDao.updateStudent(student);
        return getStudentById(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student student = getStudentById(studentId);
        Course course = courseDao.findCourseById(courseId);

        if (student.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student Not Found");
        } else if (course.getCourseName().equals("Course Not Found")) {
            System.out.println("Course Not Found");
        } else {
            studentDao.deleteStudentFromCourse(studentId, courseId);
            System.out.println("Student: " + studentId + " deleted from course: " + courseId);
        }


        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student student = getStudentById(studentId);
        Course course = courseDao.findCourseById(courseId);

        try {
            if (student.getStudentFirstName().equals("Student Not Found")) {
                System.out.println("Student Not Found");
            } else if (course.getCourseName().equals("Course Not Found")) {
                System.out.println("Course Not Found");
            } else {
                studentDao.addStudentToCourse(studentId, courseId);
                System.out.println("Student: " + studentId + " added from course: " + courseId);
            }
        } catch (DataAccessException ex) {
            System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
        }

        //YOUR CODE ENDS HERE
    }
}
