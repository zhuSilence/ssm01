package com.silence.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuxiang on 2015/11/25.
 * Desc :
 */
public class WebUtil {

    public static Map<String, Object> getQueryParameter(HttpServletRequest request) {
        return getParametersStartingWith(request, "queryParameter");
    }

    public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
        HashMap map = new HashMap();
        Enumeration names = request.getParameterNames();

        while(names.hasMoreElements()) {
            String name = (String)names.nextElement();
            String[] param = name.split("\\.");
            if(param.length >= 2 && StringUtils.equals(param[0], prefix)) {
                String[] key = new String[param.length - 1];
                System.arraycopy(param, 1, key, 0, key.length);
                map.put(StringUtils.join(key, "."), request.getParameter(name));
            }
        }

        return map;
    }
}
