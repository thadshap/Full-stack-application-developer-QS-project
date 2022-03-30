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
class StudentInQueueRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllWhereStatusInQueue() {
    }

    @Test
    void findStatusInQueueWhereStudentId() {
    }

    @Test
    void findStudentWherePlacementInQueue() {
    }

    @Test
    void findAllWhereStudentIdAndQueueId() {
    }

    @Test
    void findAllWhereQueueId() {
    }

    @Test
    void findAllWhereQueueIdAndDigitalTrue() {
    }

    @Test
    void findAllWhereQueueIdAndDigitalFalse() {
    }

    @Test
    void findAllWhereCampus() {
    }

    @Test
    void findAllWhereBuilding() {
    }

    @Test
    void findAllWhereRoom() {
    }

    @Test
    void findAllWhereTableNumber() {
    }

    @Test
    void findAllWhereCampusAndBuildingAndRoomAndTableNumber() {
    }
}
