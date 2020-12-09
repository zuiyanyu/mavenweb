package com.spring_stu.spring_property_placeholder.DES;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class DESUtils {
    //指定DES加密解密所使用的密钥
    private static Key key ;
    private static String KEY_STR = "myKey";

    static {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES") ;
            keyGenerator.init(new SecureRandom(KEY_STR.getBytes()));
            key = keyGenerator.generateKey();
            keyGenerator = null ;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt：使...加密
     * 对字符串进行DES加密，返回BASE64编码的加密字符串。
     * @param str
     * @return
     */
    public static String getEncryptString(String str){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try{
            byte[] strBytes = str.getBytes("UTF8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return  base64Encoder.encode(encryptStrBytes);
        }catch (Exception e ){
            throw new RuntimeException(e);
        }
    }
    /**
     * 对BASE64的加密字符串进行解密，返回解密后的字符串
     * cipher : 密码      Decrypt：解密
     */
    public static String getDecryptString(String str){
        BASE64Decoder base64Decoder = new BASE64Decoder() ;

        try{
            byte[] bytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES") ;
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] decryptStrBytes = cipher.doFinal(bytes);
            return new String(decryptStrBytes,"UTF8");
        }catch (Exception e ){
            throw  new RuntimeException(e) ;
        }
    }

    public static void main(String[] args) {
        //对入参的字符串进行加密，打印出加密后的串
        args = "a,b,c".split(",") ;
        for (String arg : args) {
            System.out.println(arg+":"+getEncryptString(arg));
        }
    }

}















































