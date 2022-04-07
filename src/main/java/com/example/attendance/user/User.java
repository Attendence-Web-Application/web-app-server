package com.example.attendance.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

import com.example.attendance.classroom.Course;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private int role_id;

//    @ManyToMany
//    @JoinTable(name = "class_enrolled", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
//    List<Course> enrollClasses;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    List<ClassEnroll> enrolls;
}
