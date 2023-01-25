package com.example.spring_boot.dao;

import com.example.spring_boot.Dao.StudentImpl;
import com.example.spring_boot.Entity.Student;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase       // use the test database not the original
@Transactional
@AutoConfigureTestEntityManager   // uses a test EntityManager
@DirtiesContext                  // will delete after the test are used in this storage
public class StudentDaoImplTest {

    @Autowired
    TestEntityManager em;
    @Autowired
    StudentImpl testObject;

    String createdStudentId1;
    String createdStudentId2;

    public void setup() {
        Student studentData = new Student("firstname", "lastname", "email@test.email", LocalDate.parse("2000-01-01"));
        Student studentData2 = new Student("David", "Svantesson", "david.svantesson@.email", LocalDate.parse("2003-06-17"));

        Student createdStudent1 = em.persist(studentData);
        Student createdStudent2 = em.persist(studentData2);

        createdStudentId1 = createdStudent1.getId();
        createdStudentId2 = createdStudent2.getId();
    }
@Test
    public void persist(){
        Student studentData3 = new Student("test", "test", "test@test.email", LocalDate.parse("1000-01-01"));
        Student createdStudent = testObject.persist(studentData3);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getId());
    }


}
