package com.yiwu.changething.sec1.utils;

import com.alibaba.fastjson.JSON;
import com.yiwu.changething.sec1.config.WxConfig;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private final static String ENCODE = "UTF-8";

	/**
	 * HTTP GET
	 */
	public static String get(String url, Map<String, String> params) {
		String resp = null;
		long timeStart = System.currentTimeMillis();
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);

		client.getParams().setContentCharset(ENCODE);
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

		if (params != null) {
			List<NameValuePair> nameValueList = new ArrayList<>();
			for (Map.Entry<String, String> each : params.entrySet()) {
				nameValueList.add(new NameValuePair(each.getKey(), each.getValue()));
			}
			method.setQueryString(nameValueList.toArray(new NameValuePair[] {}));
		}

		logger.info(">>>请求地址:{},参数:{}", url, method.getQueryString());
		try {
			client.executeMethod(method);
			resp = method.getResponseBodyAsString();
			long timeEnd = System.currentTimeMillis();
			logger.info("返回结果:{},耗时:{}", resp, (timeEnd - timeStart));
		} catch (Exception e) {
			logger.error(">>>请求异常", e);
		}
		return resp;
	}

	public static String post(String url, Map<String, String> params, String requestBody, boolean jsonFlag) {
		String resp = null;
		logger.info(">>>请求地址:{},params:{},requestBody:{}", url, JSON.toJSONString(params), requestBody);
		long timeStart = System.currentTimeMillis();
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);

		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

		try {
			if (params != null) {
				List<NameValuePair> nameValueList = new ArrayList<>();
				for (Map.Entry<String, String> each : params.entrySet()) {
					nameValueList.add(new NameValuePair(each.getKey(), each.getValue()));
				}
				method.setRequestBody(nameValueList.toArray(new NameValuePair[] {}));
			} else if (requestBody != null) {
				RequestEntity requestEntity = new StringRequestEntity(requestBody, null, WxConfig.ENCODE_UTF8);
				method.setRequestEntity(requestEntity);
				if (jsonFlag) {
					method.setRequestHeader("ContentType", "application/json");
				}
			}

			client.executeMethod(method);
			resp = method.getResponseBodyAsString();
			long timeEnd = System.currentTimeMillis();
			logger.info("返回结果:{},耗时:{}", resp, (timeEnd - timeStart));
		} catch (Exception e) {
			logger.error(">>>请求异常", e);
		}
		return resp;

	}

}