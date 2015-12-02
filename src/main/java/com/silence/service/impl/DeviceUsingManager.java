package com.silence.service.impl;

import com.silence.mapper.extend.DeviceMapperExtend;
import com.silence.mapper.extend.DeviceUsingMapperExtend;
import com.silence.po.BeUsingVo;
import com.silence.po.Device;
import com.silence.service.DeviceUsingService;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
@Service
@Transactional(readOnly = true)
public class DeviceUsingManager implements DeviceUsingService {

    @Autowired
    private DeviceUsingMapperExtend deviceUsingMapperExtend;

    @Autowired
    private DeviceMapperExtend deviceMapperExtend;

    /**
     * 查询出所有的设备的使用信息信息
     * @return
     * @throws Exception
     */
    public List<BeUsingVo> getDeviceUsingList(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        List<Device> deviceList = deviceMapperExtend.selectDeviceList(userQueryVo);
        List<BeUsingVo> beUsingVoList = deviceUsingMapperExtend.selectBeUsingList(userQueryVo);
        if (beUsingVoList != null && beUsingVoList.size() > 0){
            return beUsingVoList;
        }else {
            return null;
        }
    }

    /**
     * 查询出所有的设备使用记录条数
     * @return
     * @throws Exception
     */
    public int getDeviceUsingListSize(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setPageable(pageable);
        userQueryVo.setMap(map);
        Integer userListSize = deviceUsingMapperExtend.selectAllBeUsingListSize(userQueryVo);
        if(userListSize != 0){
            return userListSize;
        }
        return 0;
    }
}
