package com.my.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import java.net.URLEncoder;

public class SignCount {
    private Long timestamp = System.currentTimeMillis();
    public String encode() throws Exception  {
        String sign ="";
        System.out.println(timestamp);
        String secret = "XXXXXXXXXXXXXXXXXXXX"; //创建机器人时，可以查看或者重置密钥
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        System.out.println(sign);
        return sign;
    }
    public Long getTimestamp(){
        return timestamp;
    }
}
