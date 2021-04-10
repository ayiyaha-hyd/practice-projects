package com.hyd.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

@Slf4j
public class ShiroSaltUtil {
    public static String generateSalt(){
        // shiro 自带的工具类生成salt
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        log.debug("salt: {}",salt);
        return salt;
    }
    public static String encryptPassword(String password,String salt){
       String encryptedpassword  = new SimpleHash(Constants.HASH_ALGORITHM, password, salt).toString();
       log.debug("encryptedpassword: {}",encryptedpassword);
        String encodedPassword = Base64.encodeBase64String(encryptedpassword.getBytes());
        log.debug("encodedPassword: {}",encodedPassword);
        return encodedPassword;
    }

    public static void main(String[] args) {
/*        String salt = ShiroSaltUtil.generateSalt();
        String rawPassword = "123456";

        String encodedPassword = ShiroSaltUtil.encryptPassword(rawPassword,salt);

        log.debug("加密后的密码: {}",encodedPassword);
        String ss = new String(Base64.decodeBase64(encodedPassword.getBytes()));
        log.debug(ss);
        String s = "abc123456";
        String s1 = Base64.encodeBase64String(s.getBytes());
        System.out.println(s1);
        String s2 = new String(Base64.decodeBase64(s1));
        System.out.println(s2);*/

        String password = "123456";
        String s1 = new SimpleHash(Constants.HASH_ALGORITHM,password).toString();
        log.debug(s1);
        String encodedPassword = Base64.encodeBase64String(s1.getBytes());
        log.debug(encodedPassword);
    }
}
