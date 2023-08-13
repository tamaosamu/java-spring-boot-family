package com.family.finances.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Md5Utils {



    public static String get(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] hashBytes = md5.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte hashByte: hashBytes) {
                sb.append(String.format("%02x", hashByte));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {
            Logger log = LoggerFactory.getLogger(Md5Utils.class);
            log.error("md5 error");
            return "";
        }
    }

    public static String get(String input, String salt) {
        return Md5Utils.get(salt + input);
    }

}
