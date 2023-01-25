package com.example.spring_boot.Entity;

import com.example.spring_boot.Exception.DataNotFound;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

@Entity
//@Table(name = "TBL_Student")
public class Student {
    /*  @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "Id",updatable = false)
      private int id;
  */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate birthday;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "borrower",orphanRemoval = true)
    private List<Book> borrowedBooks;

    @ManyToMany(mappedBy = "participants")
    private List<Course> enrolledCourses;

    @ManyToMany()
    @JoinTable(name = "students_competences"
            , joinColumns = @JoinColumn(name = "student_id")
            , inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private Set<Competence> competences;
    private boolean status;
    private LocalDateTime registrationDate;

    public Student() {  // you can set default value in a constructor like this
        this.status = true;
        this.registrationDate = LocalDateTime.now();
    }

    public Student(String firstName, String lastName, String email, LocalDate birthday) {
       this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
    }
    public Student(String firstName, String lastName, String email, LocalDate birthday,Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        setAddress(address);
    }

    public void borrowBook(Book book)  {
        if (book == null) throw new IllegalArgumentException("Book data was null");
        if (borrowedBooks == null) borrowedBooks = new ArrayList<>();
        borrowedBooks.add(book);
        book.setBorrower(this);

    }

    public void returnBook(Book book)  {
        if (book == null) throw new IllegalArgumentException("Book data was null");
        if (borrowedBooks != null){
            book.setBorrower(null);
            borrowedBooks.remove(book);
        }
    }

    public void enrollCourse(Course course) {
if (course == null) throw new IllegalArgumentException("Course data was null");
if (enrolledCourses != null){
    enrolledCourses.add(course);
    course.getParticipants().add(this);
}
    }
    public void unEnrollCourse(Course course) {
if (course == null) throw new IllegalArgumentException("Course data was null");
if (enrolledCourses != null){
    course.getParticipants().remove(this);
    enrolledCourses.remove(course);
}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { // is not supposed to be changed !
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) { // is not supposed to be changed !
        this.registrationDate = registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address != null) address.setStudent(this);
        this.address = address;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<Course> getEnrolledCourses() {
        if (enrolledCourses == null) enrolledCourses = new ArrayList<>();
        return enrolledCourses;
    }
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Set<Competence> getCompetences() {
        if (competences == null) competences = new HashSet<>();
        return competences;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return status == student.status && Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(birthday, student.birthday) && Objects.equals(registrationDate, student.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, birthday, status, registrationDate);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
