package com.silence.service.impl;

import com.silence.mapper.extend.FixDeviceMapperExtend;
import com.silence.po.FixDevice;
import com.silence.service.FixDeviceService;
import com.silence.utils.DateTool;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2015/12/2.
 */
@Service
@Transactional
public class FixDeviceManager implements FixDeviceService {

    @Autowired
    FixDeviceMapperExtend fixDeviceMapperExtend;

    /**
     * 查询所有修理设备记录
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<FixDevice> getFixDeviceList(Map<String,Object> map,Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);

        List<FixDevice> fixdeviceList = fixDeviceMapperExtend.selectFixDeviceList(userQueryVo);
        if(fixdeviceList != null && fixdeviceList.size() > 0){
            return fixdeviceList;
        }else{
            return null;
        }
    }

    /**
     * 获取符合查询条件的修理设备数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public int getFixDeviceListSize(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        Integer fixListSize = fixDeviceMapperExtend.selectAllFixDeviceListSize(userQueryVo);
        if(fixListSize != 0){
            return fixListSize;
        }
        return 0;
    }


    /**
     * 更新FixDevice表
     * @param map
     * @throws Exception
     */
    public void updateFixDevice(Map<String,Object> map) throws Exception{
        if(map != null && map.containsKey("row[id]")){
            FixDevice fixdevice = fixDeviceMapperExtend.selectByPrimaryKey(Integer.parseInt(map.get("row[id]").toString()));
            if(fixdevice != null){
                if(map.containsKey("row[fixer]") && !map.get("row[fixer]").toString().trim().equals("")){
                    fixdevice.setFixer(map.get("row[fixer]").toString());
                }
                if(map.containsKey("row[d_id]") && !map.get("row[d_id]").toString().trim().equals("")){

                    if(!map.get("row[d_id]").toString().trim().equals(fixdevice.getD_id())){
                        fixdevice.setD_id(Integer.parseInt(map.get("row[d_id]").toString()));
                    }
                }

                if(map.containsKey("row[fix_time]")){
                    Date date = DateTool.timestampToDate(map.get("row[fix_time]").toString());
                    fixdevice.setFix_time(date);
                }

                if(map.containsKey("row[fix_mark]") && !map.get("row[fix_mark]").toString().trim().equals("")){
                    if(!map.get("row[fix_mark]").toString().trim().equals(fixdevice.getFix_mark())) {
                        fixdevice.setFix_mark(map.get("row[fix_mark]").toString());
                    }
                }

                if(Boolean.valueOf(map.get("row[is_fixed]").toString())){
                    fixdevice.setIs_fixed(Boolean.TRUE);
                }else {
                    fixdevice.setIs_fixed(Boolean.FALSE);
                }
                fixDeviceMapperExtend.updateByPrimaryKeySelective(fixdevice);
            }
        }
    }

    /**
     * 新增FixDevice表
     * @param map
     * @return
     * @throws Exception
     */
    public String insertFixDevice(Map<String,Object> map) throws Exception{
        if(map != null){
            FixDevice fixdevice = new FixDevice();
            if(map.containsKey("row[fixer]") && !map.get("row[fixer]").toString().trim().equals("")){
                fixdevice.setFixer(map.get("row[fixer]").toString().trim());
            }
            if(map.containsKey("row[d_id]") && !map.get("row[d_id]").toString().trim().equals("")){
                fixdevice.setD_id(Integer.parseInt(map.get("row[d_id]").toString()));
            }

            if(map.containsKey("row[fix_time]")){
                Date date = DateTool.standardStringToDate(map.get("row[fix_time]").toString(), "yyyy-MM-dd HH:mm:ss");
                fixdevice.setFix_time(date);
            }
            if(map.containsKey("row[fix_mark]") && !map.get("row[fix_mark]").toString().trim().equals("")){
                fixdevice.setFix_mark(map.get("row[fix_mark]").toString().trim());
            }

            if(Boolean.valueOf(map.get("row[is_fixed]").toString())){
                fixdevice.setIs_fixed(Boolean.TRUE);
            }else {
                fixdevice.setIs_fixed(Boolean.FALSE);
            }

            fixDeviceMapperExtend.insertSelective(fixdevice);
        }
        return "success";
    }


    /**
     * 删除某一修理设备信息
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteFixDeviceById(Integer id) throws Exception{
        int rows = fixDeviceMapperExtend.deleteByPrimaryKey(id);
        if (rows > 0) {
            return rows;
        }
        return 0;
    }
}
