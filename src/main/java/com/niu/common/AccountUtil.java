package com.niu.common;

import java.security.MessageDigest;
import java.util.Random;

public class AccountUtil {
    /**
     * 生成salt
     * @return
     */
    public static String generateSalt() {
        Random random = new Random();
        StringBuilder saltBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            saltBuilder.append(random.nextInt(10));
        }
        return saltBuilder.toString();
    }

    /**
     * 加密密码
     * @param password
     * @param salt
     * @return
     */
    public static String hashPassword(String password, String salt) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((password + salt).getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 校验密码是否正确
     * @param password
     * @param salt
     * @param storedPassword
     * @return
     */
    public static boolean checkPassword(String password, String salt, String storedPassword) {
        String encryptedPassword = AccountUtil.hashPassword(password, salt);
        return encryptedPassword.equals(storedPassword);
    }
}
