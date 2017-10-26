package com.yiwu.changething.sec1.bean;

import lombok.Data;

//https://mp.weixin.qq.com/debug/wxadoc/dev/api/open.html#wxgetuserinfoobject
@Data
public class WxUserInfo {

	private UserInfo userInfo;// 用户信息对象，不包含 openid 等敏感信息
	private String rawData;// 不包括敏感信息的原始数据字符串，用于计算签名。
	private String signature;// 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息，参考文档 signature。
	private String encryptedData;// 包括敏感数据在内的完整用户信息的加密数据，详细见加密数据解密算法
	private String iv;// 加密算法的初始向量

	@Data
	public class UserInfo {
		private String nickName;
		private String avatarUrl;
		private Integer gender; // 性别 0：未知、1：男、2：女
		private String province;
		private String city;
		private String country;

	}


}
