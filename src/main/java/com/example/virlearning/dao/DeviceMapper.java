package com.example.virlearning.dao;

import com.example.virlearning.entity.Device;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeviceMapper {
    List<Device> getfindNameDev(String name, String dep);

    Integer insertDevice(Device Device);

    Integer deleteDevice(int id,int isdeleted);

    Integer modifyDevice(Device Device);
    List<Device> findDeviceList(PageQueryUtil pageUtil);

    int getTotalDevices(PageQueryUtil pageUtil);
}
