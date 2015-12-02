package com.silence.controller;

import com.silence.po.BuyDevice;
import com.silence.service.BuyDeviceService;
import com.silence.utils.Page;
import com.silence.utils.Pageable;
import com.silence.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2015/12/2.
 */
@Controller
@RequestMapping("/buydevice")
public class BuyDeviceController {


    @Autowired
    BuyDeviceService buyDeviceService;

    /**
     * 根据参数进行筛选查询
     * @param request
     * @param pageable
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBuyDeviceList.action")
    @ResponseBody
    public Object getBuyDeviceList(HttpServletRequest request,Pageable pageable) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage()-1)*pageable.getRows());

        List<BuyDevice> deviceList = buyDeviceService.getBuyDeviceList(map, pageable);
        if (deviceList != null && deviceList.size() > 0) {
            Page<BuyDevice> page = new Page<BuyDevice>();
            page.setTotal(buyDeviceService.getBuyDeviceListSize(map, pageable));
            page.setRows(deviceList);
            return page;
        }else {
            return null;
        }
    }

    /**
     * 更新某一购买设备信息
     * @return
     */
    @RequestMapping(value = "/updateBuyDevice.action", method = RequestMethod.POST)
    @ResponseBody public String updateUser(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        buyDeviceService.updateBuyDevice(map);
        return "success";
    }


    /**
     * 新增某一购买设备记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertBuyDevice.action", method = RequestMethod.POST)
    @ResponseBody public String insertBuyDevice(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        if(map != null){
            return buyDeviceService.insertBuyDevice(map);
        }else {
            return "error";
        }
    }

    /**
     * 删除某一购买设备的信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteBuyDevice.action", method = RequestMethod.POST)
    @ResponseBody
    public String deleteBuyDeviceById(HttpServletRequest request)throws Exception{
        String ids = request.getParameter("ids");
        String[] stringIds = ids.split(",");
        Integer[] integerIds = new Integer[stringIds.length];
        for (int i = 0; i < stringIds.length; i++){
            integerIds[i] = Integer.parseInt(stringIds[i]);
            buyDeviceService.deleteBuyDeviceById(integerIds[i]);
        }
        return "success";
    }

}
