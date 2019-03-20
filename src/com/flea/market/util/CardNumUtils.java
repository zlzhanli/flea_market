package com.flea.market.util;




import com.flea.market.entity.CardNumberInfo;
import com.flea.market.entity.CareNumResult;
import com.flea.market.entity.Result;
import net.sf.json.JSONObject;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public class CardNumUtils {
    public static Result checkCardNum(String num) throws IOException, ParseException {
        Result cardNumberCheckResult=new Result();

        //截取日期转换判断 判断日期是不是有效的日期
        String dateStr=num.substring(6,14);
        System.out.println(dateStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date strToDate = null;
            strToDate = sdf.parse(dateStr);
        if(strToDate.compareTo(new Date())>=0 || num.matches("^[1-6][1-7]") ){
            cardNumberCheckResult.setMsg("请输入正确的身份证号");
            cardNumberCheckResult.setCode(100);
            return cardNumberCheckResult;
        }
        URL url=new URL("http://apicloud.mob.com/idcard/query?key=26a2540b1c814&cardno="+num);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5*1000);
        connection.connect();
        InputStream inputStream=connection.getInputStream();
        byte[] data=new byte[1024];
        int len=0;
        StringBuffer sb=new StringBuffer();
        while( (len=inputStream.read(data))>-1){
            sb.append(new java.lang.String(data,0,len));
        }

        JSONObject jsonObject=JSONObject.fromObject(sb.toString());
        CareNumResult info=(CareNumResult)JSONObject.toBean(jsonObject, CareNumResult.class);;

        if(info.getRetCode().equals("200")){
            cardNumberCheckResult.setCode(200);
            cardNumberCheckResult.setMsg("成功");
            cardNumberCheckResult.setTarget(info.getResult());
            return cardNumberCheckResult;

        }
        if(info.getRetCode().equals("20602")){
            cardNumberCheckResult.setCode(20206);
            cardNumberCheckResult.setMsg("身份证与姓名不匹配");
            return cardNumberCheckResult;
        }
        cardNumberCheckResult.setCode(Integer.parseInt(info.getRetCode()));
        cardNumberCheckResult.setMsg(info.getMsg());
        return cardNumberCheckResult;
    }
}
