package com.tory.springbootdemo.service.impl;

import com.tory.springbootdemo.dao.AreaDao;
import com.tory.springbootdemo.entity.Area;
import com.tory.springbootdemo.exception.AreaException;
import com.tory.springbootdemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    @Transactional
    public boolean addArea(Area area) {
        // 空值判断，主要是判断areaName不为空
        if (area.getAreaName() != null && !"".equals(area.getAreaName())) {
            // 设置时间
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.insertArea(area);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new AreaException("添加区域信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("添加区域信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    @Transactional
    public boolean deleteArea(int areaId) {
        if (areaId > 0) {
            try {
                // 删除区域信息
                int effectedNum = areaDao.deleteArea(areaId);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除区域信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("区域Id不能为空！");
        }
    }

    @Override
    @Transactional
    public Area updateArea(Area area) throws AreaException {
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            //如果Id合法
            if ((area.getAreaName() != null && !area.getAreaName().trim().equals("")) || area.getPriority() != null) {
                //更新内容不为空
                area.setLastEditTime(new Date());
                int count = areaDao.updateArea(area);
                if (count < 1) {
                    throw new AreaException("数据库更新失败");
                }
            } else {
                throw new AreaException("更新内容为空");
            }
        } else {
            throw new AreaException("Id不合法");
        }
        return areaDao.queryAreaById(area.getAreaId());
    }

    @Override
    public Area getAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
