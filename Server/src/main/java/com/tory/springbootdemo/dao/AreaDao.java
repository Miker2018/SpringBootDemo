package com.tory.springbootdemo.dao;

import com.tory.springbootdemo.entity.Area;

import java.util.List;

public interface AreaDao {
    //新增区域
    int insertArea(Area area);

    //删除区域
    int deleteArea(int areaId);

    //修改区域
    int updateArea(Area area);

    //查询所有区域
    List<Area> queryArea();

    //根据Id查询区域
    Area queryAreaById(int areaId);
}
