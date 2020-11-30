package com.zj.c190604.rsa;

//实现RSA算法操作，包括生成密钥，加解密，签名验签等拆操作

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class RSACode {
    static String data = "南昌的冬天忒冷了";
    public static void main(String[] args) {
        System.out.println("---------RSA加密----------");
        RSACode code = new RSACode();
        //密钥生成
        KeyPair keyPair = code.createKey(1024);
        byte[] cipherTxt = code.encrypt(data.getBytes(),keyPair.getPublic());
        System.out.println(cipherTxt);

        System.out.println("---------RSA解密----------");
        //调用解密方法
        byte[] originalTxt = code.dectypt(cipherTxt,keyPair.getPrivate());
    }

    /**
     * 对原文数据进行md5计算
     * @param data 原文
     * @return MD5的hash值
     */
    public byte[] md5Hash(byte[] data){
        //hash算法:消息摘要,Message Digest
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //========实现 私钥签名，公钥验签=========//

    /**
     * 对签名进行验证
     * @param sign 要验证的签名数据
     * @param data 原文
     * @param pub 公钥
     * @return 签名验证是否通过
     */
    public boolean verify(byte[] sign,byte[] data, PublicKey pub){
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(pub);
            byte[] hash = md5Hash(data);
            signature.update(hash);
            return signature.verify(sign);//原文hash
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 对数据进行签名
     * @param data 原文
     * @param pri 私钥
     * @return 签名后的数据
     */
    public byte[] sign(byte[] data, PrivateKey pri){
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(pri);//为签名工具设置私钥
            //对原文进行hash计算
            byte[] hash = md5Hash(data);
            signature.update(hash);//设置要签名的数据
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的密钥生成的长度RSA的密钥对
     * @param size 密钥长度
     * @return 返回密钥对
     */
    //======生成密钥对=======//
    public KeyPair createKey(int size){
        try {
            //密钥生成器
            KeyPairGenerator generator =KeyPairGenerator.getInstance("RSA");
            generator.initialize(size);//设置密钥生成大长度
            KeyPair keyPair =  generator.generateKeyPair();

            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用RSA算法私钥进行解密
     * @param cipgerTxt 要解密的密文
     * @param pri 私钥
     * @return
     */
    //解密
    public byte[] dectypt(byte[] cipgerTxt,PrivateKey pri){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,pri);
            return cipher.doFinal(cipgerTxt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * s使用rsa公钥对数据进行加密
     * @param data 要加密的数据
     * @param pub 公钥
     */

    //=============公钥加密，私钥解密============//
    //加密
    public byte[] encrypt(byte[] data , PublicKey pub){
        //执行加密
        //JAVA中专门用于加密或者解密的类
        try {
           Cipher cipher =Cipher.getInstance("RSA");
           cipher.init(Cipher.ENCRYPT_MODE,pub);//设置Cipher的工作模式
           return cipher.doFinal(data);//真正加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
