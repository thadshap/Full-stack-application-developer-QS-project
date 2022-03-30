package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // get all where class = student
    // get all where class = administrator
    // get all where class = teacher

    // get all teachers where course = x
    // get all students where course = x
    // for course x, get all students and teachers
    // update last name
    // update first name
    // update email
    // update pronouns
    //

}
