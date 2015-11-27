/**
 * Created by 朱翔 on 2015/11/25.
 */
//将时间戳转化为时间字符串
function toDateString(value){
    var d = new Date(value);
    var month = d.getMonth() + 1;
    if(month < 10){
        month = '0'+ month;
    }
    var date = d.getDate();
    if(date < 10){
        date = '0'+ date;
    }
    var hours = d.getHours();
    if(hours < 10){
        hours = '0'+ hours;
    }
    var minutes = d.getMinutes();
    if(minutes < 10){
        minutes = '0'+ minutes;
    }
    var seconds = d.getSeconds();
    if(seconds < 10){
        seconds = '0'+ seconds;
    }
    var date = (d.getFullYear()) + "-" +
        (month) + "-" +
        (date) + " " +
        (hours) + ":" +
        (minutes) + ":" +
        (seconds);
    return date;
}
