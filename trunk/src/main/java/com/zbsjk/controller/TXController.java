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

	    /* 请求方法类型 POST、GET */
	    config.put("RequestMethod", "GET");

	    /* 区域参数，可选: gz: 广州; sh: 上海; hk: 香港; ca: 北美; 等。 */
	    config.put("DefaultRegion", "gz");

	    QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Sts(),
	            config);

	    TreeMap<String, Object> params = new TreeMap<String, Object>();
	    /* 将需要输入的参数都放入 params 里面，必选参数是必填的。 */
	    /* DescribeInstances 接口的部分可选参数如下 */
	    params.put("name", "fdsafgra");
	    String policy = "{\"statement\": [{\"action\": [\"name/cos:*\"],\"effect\": \"allow\",\"resource\":[\"qcs::cos:ap-guangzhou:uid/1251784278:prefix//1251784278/test/*\"]}],\"version\": \"2.0\"}";
	    params.put("policy", policy);

	    /* 在这里指定所要用的签名算法，不指定默认为 HmacSHA1*/
	    //params.put("SignatureMethod", "HmacSHA256");

	    /* generateUrl 方法生成请求串, 可用于调试使用 */
	   // System.out.println(module.generateUrl("GetFederationToken", params));
	    String result = null;
	    try {
	        /* call 方法正式向指定的接口名发送请求，并把请求参数 params 传入，返回即是接口的请求结果。 */
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
