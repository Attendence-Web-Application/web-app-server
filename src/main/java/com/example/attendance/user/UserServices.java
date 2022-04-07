package com.example.attendance.user;

import com.example.attendance.exception.NotFoundException;
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
public class UserServices implements Services<User> {

    @Autowired
    UserDao userDao;

    @Override
    public User addOne(User user) {
        save(user);
        return user;
    }

    @Override
    public User getById(Integer id) {
        if(!userDao.existsById(id)){
            throw new NotFoundException();
        }
        try {
            return userDao.findById(id).orElse(null);
        }catch(RuntimeException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll(Map<String, String> aFilter) {
        return null;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User updateById(Integer anId, User anObj) {
        return null;
    }

    @Override
    public User updateAttrById(Integer anId, Map<String, String> anUpdateMap) {
        return null;
    }

    @Override
    public User save(User user) {
        try {
            userDao.save(user);
            return user;
        }catch(RuntimeException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User deleteById(Integer anId) {
        return null;
    }
}
