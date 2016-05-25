package com.silence.controller;

import com.alibaba.fastjson.JSON;
import com.silence.po.FixDevice;
import com.silence.service.FixDeviceService;
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
@RequestMapping("/fixdevice")
public class FixDeviceController {

    @Autowired
    FixDeviceService fixDeviceService;

    /**
     * 根据参数进行筛选查询
     * @param request
     * @param pageable
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getFixDeviceList.action")
    @ResponseBody
    public Object getFixDeviceList(HttpServletRequest request,Pageable pageable) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage()-1)*pageable.getRows());

        List<FixDevice> fixdeviceList = fixDeviceService.getFixDeviceList(map, pageable);
        if (fixdeviceList != null && fixdeviceList.size() > 0) {
            Page<FixDevice> page = new Page<FixDevice>();
            page.setTotal(fixDeviceService.getFixDeviceListSize(map, pageable));
            page.setRows(fixdeviceList);
            return JSON.toJSONString(page).replace("true","\"true\"").replace("false", "\"false\"");
        }else {
            return null;
        }
    }

    /**
     * 更新某一修理设备信息
     * @return
     */
    @RequestMapping(value = "/updateFixDevice.action", method = RequestMethod.POST)
    @ResponseBody public String updateUser(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        fixDeviceService.updateFixDevice(map);
        return "success";
    }


    /**
     * 新增某一修理设备记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertFixDevice.action", method = RequestMethod.POST)
    @ResponseBody public String insertFixDevice(HttpServletRequest request) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        if(map != null){
            return fixDeviceService.insertFixDevice(map);
        }else {
            return "error";
        }
    }

    /**
     * 删除某一修理设备的信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteFixDevice.action", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFixDeviceById(HttpServletRequest request)throws Exception{
        String ids = request.getParameter("ids");
        String[] stringIds = ids.split(",");
        Integer[] integerIds = new Integer[stringIds.length];
        for (int i = 0; i < stringIds.length; i++){
            integerIds[i] = Integer.parseInt(stringIds[i]);
            fixDeviceService.deleteFixDeviceById(integerIds[i]);
        }
        return "success";
    }

}
