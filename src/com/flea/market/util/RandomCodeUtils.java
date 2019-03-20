package com.flea.market.util;

import java.util.Random;

/**
 * @author LiuTianyou
 * @date 2019/3/6
 */

public class RandomCodeUtils {
    public static  String getRandomCode(int length){
        StringBuffer sb=new StringBuffer();
        Random random=new Random();
        for(int i=0;i<length;i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }
}
