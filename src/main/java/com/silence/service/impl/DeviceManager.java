package com.silence.service.impl;

import com.silence.mapper.DeviceMapper;
import com.silence.mapper.extend.DeviceMapperExtend;
import com.silence.po.Device;
import com.silence.service.DeviceService;
import com.silence.utils.MD5Util;
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

    @Autowired
    private DeviceMapper deviceMapper;

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


    /**
     *获取查询到的集合里的数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
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


    /**
     *更新设备列表
     * @param map
     * @throws Exception
     */
    public void updateDevice(Map<String,Object> map) throws Exception{
        if(map != null && map.containsKey("row[id]")){
            Device device = deviceMapperExtend.selectByPrimaryKey(Integer.parseInt(map.get("row[id]").toString()));
            if(device != null){
                if(map.containsKey("row[d_name]") && !map.get("row[d_name]").toString().trim().equals("")){
                    device.setD_name(map.get("row[d_name]").toString());
                }
                if(map.containsKey("row[d_desc]") && !map.get("row[d_desc]").toString().trim().equals("")){

                        if(!map.get("row[d_desc]").toString().trim().equals(device.getD_desc())){
                            device.setD_desc(map.get("row[d_desc]").toString());
                        }
                }
                if(map.containsKey("row[d_price]") && !map.get("row[d_price]").toString().trim().equals("")){
                    if(Double.parseDouble(map.get("row[d_price]").toString()) != device.getD_price()){
                        device.setD_price(Double.parseDouble(map.get("row[d_price]").toString()));
                    }
                }
                deviceMapperExtend.updateByPrimaryKeySelective(device);
            }
        }
    }


    /**
     * 新增设备记录
     * @param map
     * @return
     * @throws Exception
     */
    public String insertDevice(Map<String,Object> map) throws Exception{
        if(map != null){
            Device device = new Device();
            if(map.containsKey("row[d_name]") && !map.get("row[d_name]").toString().trim().equals("")){
                device.setD_name(map.get("row[d_name]").toString().trim());
            }
            if(map.containsKey("row[d_desc]") && !map.get("row[d_desc]").toString().trim().equals("")){
                device.setD_desc(map.get("row[d_desc]").toString().trim());
            }
            if(map.containsKey("row[d_price]") && !map.get("row[d_price]").toString().trim().equals("")){
                device.setD_price(Double.parseDouble(map.get("row[d_price]").toString()));
            }
            deviceMapperExtend.insertSelective(device);
        }
        return "success";
    }

    /**
     * 删除某一设备的信息
     * @param id
     * @throws Exception
     */
    public Integer deleteDeviceById(Integer id) throws Exception{
        int rows = deviceMapperExtend.deleteByPrimaryKey(id);
        if (rows > 0) {
            return rows;
        }
        return 0;
    }

}
