package com.example.spring_boot;

import com.example.spring_boot.Dao.*;
import com.example.spring_boot.Entity.*;
import com.example.spring_boot.Exception.DataNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    @Autowired
    StudentDao studentDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CompetenceDao competenceDao;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        ex5();
    }

    public void ex1() {
        System.out.println("########################");
        Student createdStudent = studentDao.persist(
                new Student(
                        "Joe",
                        "Lind",
                        "test.test@test.se",
                        LocalDate.parse("2000-01-01"))
        );

        System.out.println("########################" + createdStudent.getId());
    }

    public void ex2() {
        Student createdStudent = studentDao.persist(
                new Student(
                        "Joe",
                        "Lind",
                        "test.test@test.se",
                        LocalDate.parse("2000-01-01"),
                        new Address("karlskrona", "torget", "SBK"
                        )));
        addressDao.findAll().forEach(address -> {
            System.out.println("########################");
            System.out.println(address);
            System.out.println(address.getStudent());
            System.out.println("########################");
        });
    }

    public void ex3() {
        Student createdStudent = studentDao.persist(
                new Student(
                        "Joe",
                        "Lind",
                        "test.test@test.se",
                        LocalDate.parse("2000-01-01"),
                        new Address("karlskrona", "torget", "SBK"
                        )));
        Book book1Data = new Book("Book1");
        Book book2Data = new Book("Book2");

        Book createdBook1 = bookDao.persist(book1Data);
        Book createdBook2 = bookDao.persist(book2Data);

        createdStudent.borrowBook(createdBook1);
        System.out.println("########################");
        Optional<Student> optionalStudent = studentDao.findById(createdStudent.getId());
        if (optionalStudent.isPresent()) {
            System.out.println(optionalStudent.get().getBorrowedBooks().size());
        }
        System.out.println("########################");
        createdStudent.borrowBook(createdBook2);
        createdStudent.returnBook(createdBook1);
    }

    public void ex4() {
        Student createdStudent = studentDao.persist(
                new Student(
                        "Joe",
                        "Lind",
                        "test.test@test.se",
                        LocalDate.parse("2000-01-01"),
                        new Address("karlskrona", "torget", "SBK"
                        )));
        Course java = new Course("Java");
        Course csharp = new Course("C#");

        Course createdCourseJava = courseDao.persist(java);
        Course createdCourseCsharp = courseDao.persist(csharp);


createdCourseJava.addStudent(createdStudent);
createdCourseJava.removeStudent(createdStudent);
createdCourseCsharp.addStudent(createdStudent);

    }
public  void ex5(){
    Student createdStudent = studentDao.persist(
            new Student(
                    "Joe",
                    "Lind",
                    "test.test@test.se",
                    LocalDate.parse("2000-01-01"),
                    new Address("karlskrona", "torget", "SBK"
                    )));
    Competence java = competenceDao.persist(new Competence("Java SE"));
    Competence spring = competenceDao.persist(new Competence("Spring Core"));
    Competence springBoot = competenceDao.persist(new Competence("Spring Boot"));

    java.addStudent(createdStudent);
    springBoot.addStudent(createdStudent);
}

}
