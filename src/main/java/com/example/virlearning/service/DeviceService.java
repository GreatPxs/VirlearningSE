package com.example.virlearning.service;

import com.example.virlearning.entity.Device;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;

import java.util.List;

public interface DeviceService {
    List<Device> getfindNameDev(String name, String dep);
    Integer insertDevice(Device Device);

    Integer deleteDevice(Integer id,Integer Device);
    Boolean modifyDevice(Device Device);
    PageResult getDevicesPage(PageQueryUtil pageUtil);
}
