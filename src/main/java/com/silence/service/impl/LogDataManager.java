package com.silence.service.impl;

import com.silence.mapper.extend.LogDataMapperExtend;
import com.silence.po.Logdata;
import com.silence.service.LogDataService;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/29.
 */
@Service
@Transactional
public class LogDataManager implements LogDataService {

    @Autowired
    private LogDataMapperExtend logdataMapper;

    public List<Logdata> getLogDataList(Map<String, Object> map, Pageable pageable) throws Exception {
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);

        List<Logdata> LogdataList = logdataMapper.selectLogDataList(userQueryVo);
        if(LogdataList != null && LogdataList.size() > 0){
            return LogdataList;
        }else{
            return null;
        }
    }

    public int getLogDataListSize(Map<String, Object> map, Pageable pageable) throws Exception {
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        Integer userListSize = logdataMapper.selectAllDeviceListSize(userQueryVo);
        if(userListSize != 0){
            return userListSize;
        }
        return 0;
    }

    public void updateLogData(Map<String, Object> map) throws Exception {
        if(map != null && map.containsKey("row[logId]")){
            Logdata logdata = logdataMapper.selectByPrimaryKey(Integer.parseInt(map.get("row[logId]").toString()));
            if(logdata != null){
                if(map.containsKey("row[level]") && !map.get("row[level]").toString().trim().equals("")){
                    logdata.setLevel(map.get("row[level]").toString());
                }
                if(map.containsKey("row[status]") && !map.get("row[status]").toString().trim().equals("")){
                    logdata.setStatus(map.get("row[status]").toString());
                }
                if(map.containsKey("row[tester]") && !map.get("row[tester]").toString().trim().equals("")){
                    logdata.setTester(map.get("row[tester]").toString());
                }
                if(map.containsKey("row[fileurl]") && !map.get("row[fileurl]").toString().trim().equals("")){
                    logdata.setFileurl(map.get("row[fileurl]").toString());
                }
                logdataMapper.updateByPrimaryKeySelective(logdata);
            }
        }
    }

    public String insertLogData(Map<String, Object> map) throws Exception {
        if(map != null){
            Logdata logData = new Logdata();
            if(map.containsKey("row[level]") && !map.get("row[level]").toString().trim().equals("")){
                logData.setLevel(map.get("row[level]").toString());
            }
            if(map.containsKey("row[status]") && !map.get("row[status]").toString().trim().equals("")){
                logData.setStatus(map.get("row[status]").toString());
            }
            if(map.containsKey("row[tester]") && !map.get("row[tester]").toString().trim().equals("")){
                logData.setTester(map.get("row[tester]").toString());
            }
            if(map.containsKey("row[fileurl]") && !map.get("row[fileurl]").toString().trim().equals("")){
                logData.setFileurl(map.get("row[fileurl]").toString());
            }
            logdataMapper.insertSelective(logData);
        }
        return "success";
    }

    public Integer deleteLogDataById(Integer id) throws Exception {
        int rows = logdataMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return rows;
        }
        return 0;
    }
}
