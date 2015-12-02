package com.silence.controller;

import com.alibaba.fastjson.JSON;
import com.silence.po.BeUsing;
import com.silence.po.BeUsingVo;
import com.silence.service.DeviceUsingService;
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
 * Created by zhuxiang on 2015/11/24.
 * Desc :
 */
@Controller
@RequestMapping("/using")
public class DeviceUsingController {

    @Autowired
    private DeviceUsingService deviceUsingService;

    /**
     * 接收页面查询参数和分页条件进行相应的数据查询
     * @param request
     * @param pageable
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDeviceUsingList.action", method = RequestMethod.POST)
    @ResponseBody
    public Object getDeviceUsingList(HttpServletRequest request,Pageable pageable) throws Exception{
        Map<String,Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage() - 1) * pageable.getRows());
        List<BeUsingVo> beUsingVoList = deviceUsingService.getDeviceUsingList(map, pageable);
        if (beUsingVoList != null && beUsingVoList.size() > 0) {
            Page<BeUsingVo> page = new Page<BeUsingVo>();
            page.setTotal(deviceUsingService.getDeviceUsingListSize(map, pageable));
            page.setRows(beUsingVoList);
            //将生成的json字符串中的boolean的true和false替换为字符串的“true”和“false”
            //否则前台的checkbox不能自动的选中
            return JSON.toJSONString(page).replace("true","\"true\"").replace("false","\"false\"");
        }else {
            return null;
        }
    }
}
