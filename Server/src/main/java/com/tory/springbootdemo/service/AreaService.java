package com.tory.springbootdemo.service;

import com.tory.springbootdemo.entity.Area;
import com.tory.springbootdemo.exception.AreaException;

import java.util.List;

public interface AreaService {
    //创建Area对象
    boolean addArea(Area area);
    //通过Id删除Area
    boolean deleteArea(int areaId);
    //更新Area
    Area updateArea(Area area) throws AreaException;
    //通过Id查询Area
    Area getAreaById(int areaId);
    //查询所有Area
    List<Area> getAreaList();
}
