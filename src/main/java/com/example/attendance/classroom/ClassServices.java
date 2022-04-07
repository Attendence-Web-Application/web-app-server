package com.example.attendance.classroom;

import com.example.attendance.services.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Transactional
public class ClassServices implements Services<Course> {

    @Autowired
    ClassDao classDao;

    @Override
    public Course addOne(Course class_) {
        save(class_);
        return class_;
    }

    @Override
    public Course getById(Integer anId) {
        System.out.println(anId);
        System.out.println(anId + "_" + classDao.findById(anId));
        return classDao.findById(anId).get();
    }

    public List<Course> getByUserId(Integer anId) {
        return classDao.findByUserId(anId);
    }

    public List<Course> getByNumber(String number) {
        return classDao.findByNumber(number);
    }

    public List<Course> getByTitle(String title) {
        return classDao.findByTitle(title);
    }

    @Override
    public List<Course> getAll(Map<String, String> aFilter) {
        return null;
    }

    public List<Course> findAll() {
        return classDao.findAll();
    }

    @Override
    public Course updateById(Integer anId, Course anObj) {
        return null;
    }

    @Override
    public Course updateAttrById(Integer anId, Map<String, String> anUpdateMap) {
        return null;
    }

    @Override
    public Course save(Course class_) {
        try {
            classDao.save(class_);
            return class_;
        }catch(RuntimeException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Course deleteById(Integer anId) {
        Course course = getById(anId);
        classDao.deleteById(anId);
        return course;
    }

    public void deleteByNumber(String number) {
        classDao.deleteByNumber(number);
    }
}
