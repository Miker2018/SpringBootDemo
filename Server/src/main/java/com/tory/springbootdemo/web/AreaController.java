package com.tory.springbootdemo.web;

import com.tory.springbootdemo.entity.Area;
import com.tory.springbootdemo.exception.AreaException;
import com.tory.springbootdemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "area", produces = "application/json;charset=utf-8")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @PostMapping("add")
    private Map<String, Object> addArea(@RequestBody Area area) {
        Map<String, Object> modelMap = new HashMap<>();
        // 添加区域信息
        modelMap.put("success", areaService.addArea(area));
        return modelMap;
    }

    @GetMapping("remove")
    private Map<String, Object> removeArea(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 删除区域信息
        modelMap.put("success", areaService.deleteArea(areaId));
        return modelMap;
    }

    @PostMapping("update")
    private Map<String, Object> modifyArea(@RequestBody Area area) throws AreaException {
        Map<String, Object> modelMap = new HashMap<>();
        // 修改区域信息
        modelMap.put("success", areaService.updateArea(area));
        return modelMap;
    }

    @GetMapping("getById")
    private Map<String, Object> getAreaById(Integer areaId) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域信息
        Area area = areaService.getAreaById(areaId);
        modelMap.put("area", area);
        return modelMap;
    }

    @GetMapping("list")
    private Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取区域列表
        List<Area> list = areaService.getAreaList();
        modelMap.put("areaList", list);
        return modelMap;
    }
}
