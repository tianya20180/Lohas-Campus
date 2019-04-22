package com.wx.happy.service;

import com.wx.happy.BaseTest;
import com.wx.happy.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
      @Autowired
    private AreaService areaService;
      @Test
    public void testGetAreaList(){
          List<Area> areaList=areaService.getAreaList();
          assertEquals(2,areaList.size());
      }

}
