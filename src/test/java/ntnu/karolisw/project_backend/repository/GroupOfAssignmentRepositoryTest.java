package ntnu.karolisw.project_backend.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GroupOfAssignmentRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllWhereGroupIdAndOrderNr() {
    }

    @Test
    void findAllWhereCourseId() {
    }

    @Test
    void findAllWhereGroupId() {
    }

    @Test
    void findAllWhereNumberOfAssignment() {
    }

    @Test
    void findApprovedAssignmentsWhereGroupId() {
    }
}