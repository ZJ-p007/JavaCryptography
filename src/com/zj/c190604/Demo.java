package com.zj.c190604;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Demo {
    String key = "c190604";//设定的密钥
   static String data = "南昌的冬天忒冷了";

    static String str = "P007";//字符串

    public static void main(String[] args) {
        System.out.println(str);
        // System.out.println("hello word");
        Demo demo = new Demo();
        System.out.println(demo.key);
        System.out.println(demo.data);
        //加密
        byte[] cipherText = demo.encrypt(demo.key.getBytes(), demo.data.getBytes());
        System.out.println(cipherText);
    }

    public byte[] desOperation(byte[] key,byte[] data, int mode){
        //
        try {
            DESKeySpec spec = new DESKeySpec(key);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = factory.generateSecret(spec);
            //执行cipher
            Cipher cipher = Cipher.getInstance("DES");
            return cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }




    //方法一:加密
    public byte[] encrypt(byte[] key, byte[] data) {
        try {
            //生成DES密钥
            DESKeySpec desKeySpec = new DESKeySpec(key);
            //DESKeySpec desKeySpec = new DESKeySpec(key);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //执行最后的加密
            return cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            // System.out.println("出现异常");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            //最终的逻辑处理
        }
        //返回值语句
        return null;//null关键字
    }
//https://github.com/ZJ-p007/JavaCryptography.git
    //方法二:解密
    public byte[] decrypt(byte[] cipherTxt, byte[] key) {
        try {
            DESKeySpec spec = new DESKeySpec(key);
            //标准的加密算法工厂,DES算法
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = factory.generateSecret(spec);
            //执行解密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            cipher.doFinal(cipherTxt);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
//https://github.com/ZJ-p007/JavaCryptography.git

}
