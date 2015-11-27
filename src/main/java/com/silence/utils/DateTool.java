package com.silence.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTool {
 
    public static final String DATE_BASIC = "yyyy-MM-dd HH:mm:ss";
     
    /**
     * 将字符串按指定格式转换成Date
     * @param str
     * @param style
     * @return
     */
    public static Date stringToDate(String str, String style){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        try {
            if(str==null||str.equals("")){
                date=null;
            }
            else{
                date = sdf.parse(str);
            }
             
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
     
    /**
     * 将日期按指定格式转换成字符串
     * @param date
     * @param style
     * @return
     */
    public static String dateToString(Date date, String style) {
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    /**
     * 将中国标准时间转换为时间字符串
     * @param string
     * @param styler
     * @return
     * @throws Exception
     */
    public static String standardStringToDateString(String string,String styler) throws Exception {
        SimpleDateFormat sdf =new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z' (中国标准时间)'", Locale.ENGLISH);
        Date date = sdf.parse(string);
        sdf.applyPattern(styler);
        return sdf.format(date);
    }

    /**
     * 将中国标准时间转换为时间对象
     * @param string
     * @param styler
     * @return
     * @throws Exception
     */
    public static Date standardStringToDate(String string,String styler) throws Exception {
        SimpleDateFormat sdf =new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z' (中国标准时间)'", Locale.ENGLISH);
        Date date = sdf.parse(string);
        sdf.applyPattern(styler);
        return DateTool.stringToDate(sdf.format(date), styler);
    }

    /**
     * 将传入的时间戳转换成指定格式的时间字符串
     * @param timeStamp
     * @param style
     * @return
     */
    public static String timestampToString(String timeStamp, String style){
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        String date = sdf.format(new Date(Long.parseLong(timeStamp) * 1000));
        return date;
    }

    /**
     * 将传入的时间戳转换成yyyy-MM-dd HH:mm:ss格式的时间字符串
     * @param timeStamp
     * @return
     */
    public static String timestampToString(String timeStamp){
        SimpleDateFormat sdf = new SimpleDateFormat(DateTool.DATE_BASIC);
        String date = sdf.format(new Date(Long.parseLong(timeStamp) *1000));
        return date;
    }

    /**
     * 将传入的时间戳转换成Date
     * @param timeStamp
     * @param style
     * @return
     */
    public static Date timestampToDate(String timeStamp, String style){
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        String date = sdf.format(new Date(Long.parseLong(timeStamp) * 1000));
        return stringToDate(date,style);
    }


    /**
     * 将传入的时间戳转换成Date
     * @param timeStamp
     * @return
     */
    public static Date timestampToDate(String timeStamp){
        SimpleDateFormat sdf = new SimpleDateFormat(DateTool.DATE_BASIC);
        String date = sdf.format(new Date(Long.parseLong(timeStamp)));
        return stringToDate(date,DateTool.DATE_BASIC);
    }

}