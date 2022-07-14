package com.atwj.store.controller;

import com.atwj.store.entity.District;
import com.atwj.store.service.DistrictService;
import com.atwj.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-22 11:23
 */
@RequestMapping("/districts")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private DistrictService districtService;

    @GetMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> resultList = districtService.getByParent(parent);
        return new JsonResult<>(OK,resultList);
    }
}
