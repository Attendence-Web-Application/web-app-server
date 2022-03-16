package com.example.attendance.class_enrolled;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ClassUserKey implements Serializable {

    @Column(name = "class_id")
    private int classId;

    @Column(name = "user_id")
    private int userId;

    public ClassUserKey() {
    }

    public ClassUserKey(int classId, int userId) {
        this.classId = classId;
        this.userId = userId;
    }
}
