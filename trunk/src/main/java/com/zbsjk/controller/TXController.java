package com.zbsjk.controller;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Sts;

@RestController
public class TXController {

	@RequestMapping(value = "/cosKey",method=RequestMethod.GET)
	@ResponseBody
	public Object getCosKey(@RequestParam(value="bucket",required=true) String bucket, @RequestParam(value="region",required=false) String region,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		TreeMap<String, Object> config = new TreeMap<String, Object>();
	    config.put("SecretId", "AKID51jBNJFPZUFaswfLflcEarujKgoh7r77");
	    config.put("SecretKey", "JOFrDJAfuud8NM6duwjHZbOwGK8g07zd");

	    /* ���󷽷����� POST��GET */
	    config.put("RequestMethod", "GET");

	    /* �����������ѡ: gz: ����; sh: �Ϻ�; hk: ���; ca: ����; �ȡ� */
	    config.put("DefaultRegion", "gz");

	    QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Sts(),
	            config);

	    TreeMap<String, Object> params = new TreeMap<String, Object>();
	    /* ����Ҫ����Ĳ��������� params ���棬��ѡ�����Ǳ���ġ� */
	    /* DescribeInstances �ӿڵĲ��ֿ�ѡ�������� */
	    params.put("name", "fdsafgra");
	    String policy = "{\"statement\": [{\"action\": [\"name/cos:*\"],\"effect\": \"allow\",\"resource\":[\"qcs::cos:ap-guangzhou:uid/1251784278:prefix//1251784278/test/*\"]}],\"version\": \"2.0\"}";
	    params.put("policy", policy);

	    /* ������ָ����Ҫ�õ�ǩ���㷨����ָ��Ĭ��Ϊ HmacSHA1*/
	    //params.put("SignatureMethod", "HmacSHA256");

	    /* generateUrl ������������, �����ڵ���ʹ�� */
	   // System.out.println(module.generateUrl("GetFederationToken", params));
	    String result = null;
	    try {
	        /* call ������ʽ��ָ���Ľӿ����������󣬲���������� params ���룬���ؼ��ǽӿڵ��������� */
	        result = module.call("GetFederationToken", params);
	        JSONObject json_result = JSONObject.parseObject(result);
	        //JSONObject json_result = new JSONObject(result);
	        return json_result;
	    } catch (Exception e) {
	        System.out.println("error..." + e.getMessage());
	    }
	    return null;
	}

}
