package com.tory.springbootdemo.dao;

import com.tory.springbootdemo.entity.Area;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    void insertArea() {
    }

    @Test
    void deleteArea() {
    }

    @Test
    void updateArea() {
    }

    @Test
    void queryArea() {
        List<Area> areaList=areaDao.queryArea();
        for (Area area :areaList) {
            System.out.println(area.getAreaName());
        }
    }

    @Test
    void queryAreaById() {
    }
}