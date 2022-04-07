package com.example.attendance.rollcall;

import com.example.attendance.services.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RollCallService implements Services<RollCall> {

    @Autowired
    RollCallDao rollCallDao;

    @Override
    public RollCall addOne(RollCall anObj) {
        return null;
    }

    @Override
    public RollCall getById(Integer anId) {
        return rollCallDao.findById(anId).get();
    }

    public List<RollCall> getByClassId(Integer anId) {
        return rollCallDao.findByClassId(anId);
    }

    @Override
    public List<RollCall> getAll(Map<String, String> aFilter) {
        return null;
    }

    public List<RollCall> findAll() {
        return rollCallDao.findAll();
    }

    @Override
    public RollCall updateById(Integer anId, RollCall anObj) {
        return rollCallDao.save(anObj);
    }

    @Override
    public RollCall updateAttrById(Integer anId, Map<String, String> anUpdateMap) {
        return null;
    }

    @Override
    public RollCall save(RollCall anObj) {
        try {
            rollCallDao.save(anObj);
            return anObj;
        }catch(RuntimeException e){
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public RollCall deleteById(Integer anId) {
        RollCall rollCall = rollCallDao.getById(anId);
        rollCallDao.deleteById(anId);
        return rollCall;
    }
}
