package com.silence.service.impl;

import com.silence.mapper.extend.BuyDeviceMapperExtend;
import com.silence.po.BuyDevice;
import com.silence.service.BuyDeviceService;
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
public class BuyDeviceManager implements BuyDeviceService {


    @Autowired
    BuyDeviceMapperExtend buyDeviceMapperExtend;

    /**
     * 查询所有购买设备记录
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public List<BuyDevice> getBuyDeviceList(Map<String,Object> map,Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);

        List<BuyDevice> buydeviceList = buyDeviceMapperExtend.selectBuyDeviceList(userQueryVo);
        if(buydeviceList != null && buydeviceList.size() > 0){
            return buydeviceList;
        }else{
            return null;
        }
    }

    /**
     * 获取符合查询条件的购买设备数量
     * @param map
     * @param pageable
     * @return
     * @throws Exception
     */
    public int getBuyDeviceListSize(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        Integer userListSize = buyDeviceMapperExtend.selectAllBuyDeviceListSize(userQueryVo);
        if(userListSize != 0){
            return userListSize;
        }
        return 0;
    }


    /**
     * 更新BuyDevice表
     * @param map
     * @throws Exception
     */
    public void updateBuyDevice(Map<String,Object> map) throws Exception{
        if(map != null && map.containsKey("row[id]")){
            BuyDevice buydevice = buyDeviceMapperExtend.selectByPrimaryKey(Integer.parseInt(map.get("row[id]").toString()));
            if(buydevice != null){
                if(map.containsKey("row[buyer]") && !map.get("row[buyer]").toString().trim().equals("")){
                    buydevice.setBuyer(map.get("row[buyer]").toString());
                }
                if(map.containsKey("row[d_id]") && !map.get("row[d_id]").toString().trim().equals("")){

                    if(!map.get("row[d_id]").toString().trim().equals(buydevice.getD_id())){
                        buydevice.setD_id(Integer.parseInt(map.get("row[d_id]").toString()));
                    }
                }
                if(map.containsKey("row[b_money]") && !map.get("row[b_money]").toString().trim().equals("")){
                    if(Double.parseDouble(map.get("row[b_money]").toString()) != buydevice.getB_money()){
                        buydevice.setB_money(Double.parseDouble(map.get("row[b_money]").toString()));
                    }
                }

                if(map.containsKey("row[b_time]")){
                    Date date = DateTool.timestampToDate(map.get("row[b_time]").toString());
                    buydevice.setB_time(date);
                }

                if(map.containsKey("row[b_mark]") && !map.get("row[b_mark]").toString().trim().equals("")){
                    if(!map.get("row[b_mark]").toString().trim().equals(buydevice.getB_mark())) {
                        buydevice.setB_mark(map.get("row[b_mark]").toString());
                    }
                }
                buyDeviceMapperExtend.updateByPrimaryKeySelective(buydevice);
            }
        }
    }

    /**
     * 新增BuyDevice表
     * @param map
     * @return
     * @throws Exception
     */
    public String insertBuyDevice(Map<String,Object> map) throws Exception{
        if(map != null){
            BuyDevice buydevice = new BuyDevice();
            if(map.containsKey("row[buyer]") && !map.get("row[buyer]").toString().trim().equals("")){
                buydevice.setBuyer(map.get("row[buyer]").toString().trim());
            }
            if(map.containsKey("row[d_id]") && !map.get("row[d_id]").toString().trim().equals("")){
                buydevice.setD_id(Integer.parseInt(map.get("row[d_id]").toString()));
            }
            if(map.containsKey("row[b_money]") && !map.get("row[b_money]").toString().trim().equals("")){
                buydevice.setB_money(Double.parseDouble(map.get("row[b_money]").toString()));
            }
            if(map.containsKey("row[b_time]")){
                Date date = DateTool.standardStringToDate(map.get("row[b_time]").toString(), "yyyy-MM-dd HH:mm:ss");
                buydevice.setB_time(date);
            }
            if(map.containsKey("row[b_mark]") && !map.get("row[b_mark]").toString().trim().equals("")){
                buydevice.setB_mark(map.get("row[b_mark]").toString().trim());
            }

            buyDeviceMapperExtend.insertSelective(buydevice);
        }
        return "success";
    }


    /**
     * 删除某一购买设备信息
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteBuyDeviceById(Integer id) throws Exception{
        int rows = buyDeviceMapperExtend.deleteByPrimaryKey(id);
        if (rows > 0) {
            return rows;
        }
        return 0;
    }
}
