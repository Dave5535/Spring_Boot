package com.example.spring_boot.Entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    @ManyToMany()
    @JoinTable(name = "students_competences"
            , joinColumns = @JoinColumn(name = "competence_id")
            , inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    public Competence() {
    }

    public Competence(String name) {
        this.name = name;
    }

    public Competence(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student data was null");
        if (students == null) students = new HashSet<>();
        student.getCompetences().contains(this);
        students.add(student);

    }

    public void removeStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student data was null");
        if (students == null) students = new HashSet<>();
        student.getCompetences().remove(this);
        students.remove(student);
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

    public Set<Student> getStudents() {
        if (students == null) students = new HashSet<>();
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
