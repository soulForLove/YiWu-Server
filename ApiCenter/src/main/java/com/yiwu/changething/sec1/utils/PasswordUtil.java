package com.yiwu.changething.sec1.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class PasswordUtil {
    private static final int HASH_INTERACTIONS = 1024;

    public static final String HASH_ALGORITHM = "MD5";
    public static final int HASH_INTERATIONS = 2;
    public static final String HASH_SALT = "5bd6b9691273c47db7002fc80b5896";

    /**
     * 生成安全的密码
     */
    public static String entryptPassword(String plainPassword) {
        byte[] hashPassword = Digests.md5(plainPassword.getBytes(), HASH_SALT.getBytes(), HASH_INTERATIONS);
        return Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        return password.equals(entryptPassword(plainPassword));
    }

    /**
     * 进行混淆比对
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encrypt(String password, String salt) {
        return new Sha256Hash(password, ByteSource.Util.bytes(Base64.decode(salt)), HASH_INTERACTIONS).toBase64();
    }

    /**
     * 获取混淆值
     *
     * @return
     */
    public static String generateSaltStr() {
        return generateSalt().toBase64();
    }

    private static ByteSource generateSalt() {
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        return rng.nextBytes();
    }
}
