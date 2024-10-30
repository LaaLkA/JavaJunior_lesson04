package org.example.seminar.Models;

import jakarta.persistence.*;
import java.util.Random;


@Entity
@Table(name = "students")
public class Student {

    private static final String[] NAMES = new String[]
            {"Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Влад", "Никита"};
    private static final Random RANDOM = new Random();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column
    private int age;

    public Student() {

    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Student create() {
        return new Student(NAMES[RANDOM.nextInt(NAMES.length)], RANDOM.nextInt(18, 30));
    }

    public void updateAge() {
        age = RANDOM.nextInt(10);
    }

    public void updateName() {
        name = NAMES[RANDOM.nextInt(NAMES.length)];
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
