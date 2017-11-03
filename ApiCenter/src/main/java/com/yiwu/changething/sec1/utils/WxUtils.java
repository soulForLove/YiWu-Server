package com.yiwu.changething.sec1.utils;

import com.yiwu.changething.sec1.config.WxConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@SuppressWarnings("unchecked")
public class WxUtils {

    protected static Logger logger = LoggerFactory.getLogger(WxUtils.class);

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    public static String getSign(SortedMap<String, String> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : packageParams.entrySet()) {
            if (!"sign".equals(entry.getKey()) && !"key".equals(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        sb.append("key=" + key);
        String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        logger.debug(">>>>string:" + sb.toString());
        logger.debug(">>>>key:" + key);
        logger.debug(">>>>sign:" + sign);
        return sign;
    }

    /**
     * 添加签名并封装xml
     */
    public static String wrapXmlWithSign(SortedMap<String, String> treeMap, String key) {
        treeMap.put("sign", getSign(treeMap, key));
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            if ("body".equals(entry.getKey())) {
                xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">");
            } else {
                xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">");
            }
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 解析微信返回
     */
    public static Map<String, String> parseWxResp(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader saxReader = new SAXReader();
        Document doc;
        doc = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        Element root = doc.getRootElement();
        List<Element> elements = root.elements();
        for (Element e : elements) {
            map.put(e.getName(), e.getTextTrim());
        }
        return map;
    }

    public static InputStream getQrCode(String url, String requestBody) {
        InputStream resp = null;
        logger.info(">>>请求地址:{},参数:{}", url, requestBody);
        long timeStart = System.currentTimeMillis();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/json");

        try {
            RequestEntity requestEntity = new StringRequestEntity(requestBody, null, WxConfig.ENCODE_UTF8);
            method.setRequestEntity(requestEntity);

            client.executeMethod(method);
            resp = method.getResponseBodyAsStream();
            long timeEnd = System.currentTimeMillis();
            logger.info("返回结果:{},耗时:{}", resp, (timeEnd - timeStart));
        } catch (Exception e) {
            logger.error(">>>请求异常", e);
        }
        return resp;
    }

}
