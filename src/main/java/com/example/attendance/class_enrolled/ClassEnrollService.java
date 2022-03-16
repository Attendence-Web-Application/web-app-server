package com.example.attendance.class_enrolled;

import com.example.attendance.services.Services;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
@Transactional
public class ClassEnrollService implements Services<ClassEnroll> {

    @Autowired
    ClassEnrollDao classEnrollDao;

    @Override
    public ClassEnroll addOne(ClassEnroll classEnroll) {
        save(classEnroll);
        return classEnroll;
    }

    @Override
    public ClassEnroll getById(Integer anId) {
        return null;
    }

    public ClassEnroll getByComposeId(Integer userId, Integer classId) {
        return classEnrollDao.findByComposeId(userId, classId);
    }

    public List<Integer> getByUserId(Integer userId) {
        return classEnrollDao.findByUserId(userId);
    }

    public ClassEnroll getByClassId(Integer classId) {
        return classEnrollDao.findByClassId(classId);
    }

    @Override
    public List<ClassEnroll> getAll(Map<String, String> aFilter) {
        return null;
    }

    public List<ClassEnroll> findAll() {
        return classEnrollDao.findAll();
    }

    @Override
    public ClassEnroll updateById(Integer anId, ClassEnroll anObj) {
        return null;
    }

    @Override
    public ClassEnroll updateAttrById(Integer anId, Map<String, String> anUpdateMap) {
        return null;
    }

    @Override
    public ClassEnroll save(ClassEnroll classEnroll) {
        try {
            classEnrollDao.save(classEnroll);
            return classEnroll;
        }catch(RuntimeException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public ClassEnroll deleteById(Integer anId) {
        return null;
    }

    public void deleteByComposeId(Integer userId, Integer classId) {
        classEnrollDao.deleteByComposeId(userId, classId);
    }

}
