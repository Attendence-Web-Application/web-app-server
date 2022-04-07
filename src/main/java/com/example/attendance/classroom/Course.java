package com.example.attendance.classroom;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
@Table(name = "class")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NotNull
    private String number;

    @NotNull
    private String title;

    private Date start_date;

    private Date end_date;

    private int size;

    private int attendance_times;

    @NotNull
    private int user_id;

//    @ManyToMany(mappedBy = "enrollClasses")
//    List<User> enrolled;

//    @JsonIgnore
//    @OneToMany(mappedBy = "class_")
//    List<ClassEnroll> enrolls;
}