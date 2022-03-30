package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;

import java.util.List;

// todo use queries --> this cannot be instantiated!
public interface StudentAssistantRepository {
    // get all courses from studentAssistants where studentId = y
    List<Course> getAllWhereStudentId(Long studentId);


}
