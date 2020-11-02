package edu.utn.helper;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptHelper {

    public static String encryptPassword (String password) {
        return DigestUtils.md5Hex(password);
    }

}
