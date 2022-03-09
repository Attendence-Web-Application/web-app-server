package com.example.attendance.services;

import java.util.List;
import java.util.Map;

public interface Services<T> {
    T addOne(T anObj);
    T getById(Integer anId);
    List<T> getAll(Map<String, String> aFilter);
    T updateById(Integer anId, T anObj);
    T updateAttrById(Integer anId, Map<String, String> anUpdateMap);
    T save(T anObj);
    T deleteById(Integer anId);
}
