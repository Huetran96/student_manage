package com.servlet.studentmanage.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String address;
    String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    Classroom classroom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="student_subject",
            joinColumns =@JoinColumn(name = "student_id"),
            inverseJoinColumns =@JoinColumn(name = "subject_id")
    )
    List<Subject> subjects;
}
