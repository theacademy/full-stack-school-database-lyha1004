package mthree.com.fullstackschool.dao.mappers;

import mthree.com.fullstackschool.model.Course;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE

        Course cour = new Course();
        cour.setCourseId(rs.getInt("cid"));
        cour.setCourseName(rs.getString("courseCode"));
        cour.setCourseDesc(rs.getString("courseDesc"));
        cour.setTeacherId(rs.getInt("teacherId"));
        return cour;
        //YOUR CODE ENDS HERE
    }
}
