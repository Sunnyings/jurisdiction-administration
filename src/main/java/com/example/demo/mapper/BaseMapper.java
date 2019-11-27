package com.example.demo.mapper;

import java.util.List;

import com.example.demo.exception.DaoException;
import com.example.demo.model.BaseModel;



public interface BaseMapper<T1 extends BaseModel> {

    public List<T1> query(T1 p);

    public int insert(T1 p)throws DaoException;

    public int update(T1 p)throws DaoException;

    public int del(T1 p)throws DaoException;

    public T1 get(T1 p);

}