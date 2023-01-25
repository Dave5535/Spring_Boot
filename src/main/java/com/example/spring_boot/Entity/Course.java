package com.example.spring_boot.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
@ManyToMany
    private List<Student> participants;
    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(int id, String name, List<Student> participants) {
        this.id = id;
        this.name = name;
        this.participants = participants;
    }

    public void addStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student data was null");
        if (participants== null) participants = new ArrayList<>();
        participants.add(student);
        student.getEnrolledCourses().add(this);
    }

    public void removeStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("student data was null");
        student.getEnrolledCourses().remove(this);
        participants.remove(student);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getParticipants() {
        if (participants == null) participants = new ArrayList<>();
        return participants;
    }

    public void setStudent(List<Student> Participants) {
        this.participants = Participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
