package com.utils.DES;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 我们使用EDS算法编写加密解密工具类。
 * EDS是对称加密工具，是可进行解密的。
 * DES加密解密的最关键的是加密密钥。
 */
public class DESUtils {

    private static Key key ;
    //指定DES加密解密所使用的加密密钥
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
        //我们使用BASE64对加密后的字符串进行编码，而不是UTF8编码。
        //BASE64编码是以大小写字母、数字、以及其他几个字符组成的编码串。
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try{
            // 入参是UTF8编码的字符串。
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
        args = "root,123456".split(",") ;
        for (String arg : args) {
            System.out.println(arg+":"+getEncryptString(arg) +"->"+getDecryptString(getEncryptString(arg)));
        }
    }

}















































