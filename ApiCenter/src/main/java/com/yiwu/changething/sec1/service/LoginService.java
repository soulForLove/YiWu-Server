package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.mapper.LoginMapper;
import com.yiwu.changething.sec1.utils.Digests;
import com.yiwu.changething.sec1.utils.Encodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

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
}
