package com.silence.controller;

import com.silence.po.Device;
import com.silence.service.DeviceService;
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
 * Created by Tao on 2015/11/29.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 根据参数进行筛选查询
     * @param request
     * @param pageable
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDeviceList.action")
    @ResponseBody
    public Object getDeviceList(HttpServletRequest request,Pageable pageable) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage()-1)*pageable.getRows());

        List<Device> deviceList = deviceService.getDeviceList(map, pageable);
        if (deviceList != null && deviceList.size() > 0) {
            Page<Device> page = new Page<Device>();
            page.setTotal(deviceService.getDeviceListSize(map,pageable));
            page.setRows(deviceList);
            return page;
        }else {
            return null;
        }
    }

    /**
     * 更新某一设备信息
     * @return
     */
    @RequestMapping(value = "/updateDevice.action", method = RequestMethod.POST)
    @ResponseBody public String updateUser(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        deviceService.updateDevice(map);
        return "success";
    }


    /**
     * 新增某一设备记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertDevice.action", method = RequestMethod.POST)
    @ResponseBody public String insertDevice(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        if(map != null){
            return deviceService.insertDevice(map);
        }else {
            return "error";
        }
    }

    /**
     * 删除某一设备的信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteDevice.action", method = RequestMethod.POST)
    @ResponseBody
    public String deleteDeviceById(HttpServletRequest request)throws Exception{
        String ids = request.getParameter("ids");
        String[] stringIds = ids.split(",");
        Integer[] integerIds = new Integer[stringIds.length];
        for (int i = 0; i < stringIds.length; i++){
            integerIds[i] = Integer.parseInt(stringIds[i]);
            deviceService.deleteDeviceById(integerIds[i]);
        }
        return "success";
    }

}
