package com.example.virlearning.service.impl;

import com.example.virlearning.dao.DeviceMapper;
import com.example.virlearning.entity.Device;
import com.example.virlearning.service.DeviceService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {
    @Resource
    private DeviceMapper DeviceDao;

    public List<Device> getfindNameDev(String name, String dep){
        return DeviceDao.getfindNameDev(name,dep);
    }
    public Integer insertDevice(Device Device) {return DeviceDao.insertDevice(Device);}
    public Integer deleteDevice(Integer id,Integer isdeleted) {return DeviceDao.deleteDevice(id,isdeleted);}
    public Boolean modifyDevice(Device Device) {
        if (DeviceDao.modifyDevice(Device) > 0) {
        return true;
    }
        return false;
    }
    public PageResult getDevicesPage(PageQueryUtil pageUtil) {
        List<Device> Dept = DeviceDao.findDeviceList(pageUtil);
        int total = DeviceDao.getTotalDevices(pageUtil);
        PageResult pageResult = new PageResult(Dept, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
