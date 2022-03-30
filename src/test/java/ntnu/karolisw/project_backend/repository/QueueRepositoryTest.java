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
class QueueRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findWaitingStudentsWhereQueueId() {
    }

    @Test
    void findWaitingStudentsWhereCourseId() {
    }

    @Test
    void findQueueByQueueId() {
    }

    @Test
    void findAllWhereActiveTrue() {
    }

    @Test
    void findAllWhereActiveFalse() {
    }

    @Test
    void findAllWhereCourseId() {
    }
}
