package com.silence.controller;

import com.silence.po.Logdata;
import com.silence.service.LogDataService;
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
 * Created by zhuxiang on 2016/5/25.
 * Desc :
 */
@Controller
@RequestMapping("/logData")
public class LogDataController {

    @Autowired
    private LogDataService logDataService;

    @RequestMapping(value = "/getLogDataList.action")
    @ResponseBody
    public Object getLogDataList(HttpServletRequest request, Pageable pageable) throws Exception {
        Map<String, Object> map = WebUtil.getQueryParameter(request);
        pageable.setPage((pageable.getPage() - 1) * pageable.getRows());

        List<Logdata> logDataList = logDataService.getLogDataList(map, pageable);
        if (logDataList != null && logDataList.size() > 0) {
            Page<Logdata> page = new Page<Logdata>();
            page.setTotal(logDataService.getLogDataListSize(map, pageable));
            page.setRows(logDataList);
            return page;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/insertLogData.action", method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(HttpServletRequest request) throws Exception {
        Map<String, Object> map = WebUtil.getQueryParameter(request);
        if (map != null) {
            return logDataService.insertLogData(map);
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/updateLogData.action", method = RequestMethod.POST)
    @ResponseBody
    public String updateLogData(HttpServletRequest request) throws Exception {
        Map<String, Object> map = WebUtil.getQueryParameter(request);
        logDataService.updateLogData(map);
        return "success";
    }

    @RequestMapping(value = "/deleteLogData.action", method = RequestMethod.POST)
    @ResponseBody
    public String deleteLogData(HttpServletRequest request) throws Exception {
        String ids = request.getParameter("ids");
        String[] stringIds = ids.split(",");
        Integer[] integerIds = new Integer[stringIds.length];
        for (int i = 0; i < stringIds.length; i++) {
            integerIds[i] = Integer.parseInt(stringIds[i]);
            logDataService.deleteLogDataById(integerIds[i]);
        }
        return "success";
    }
}
