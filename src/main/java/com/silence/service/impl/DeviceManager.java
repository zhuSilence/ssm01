package com.silence.service.impl;

import com.silence.mapper.extend.DeviceMapperExtend;
import com.silence.po.Device;
import com.silence.service.DeviceService;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2015/11/29.
 */
@Service
@Transactional
public class DeviceManager implements DeviceService{

    @Autowired
    private DeviceMapperExtend deviceMapperExtend;

    /**
     * 查询所有符合条件的设备
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<Device> getDeviceList(Map<String,Object> map,Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);

        List<Device> deviceList = deviceMapperExtend.selectDeviceList(userQueryVo);
        if(deviceList != null && deviceList.size() > 0){
            return deviceList;
        }else{
            return null;
        }
    }


    public int getDeviceListSize(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        Integer userListSize = deviceMapperExtend.selectAllDeviceListSize(userQueryVo);
        if(userListSize != 0){
            return userListSize;
        }
        return 0;
    }






}
