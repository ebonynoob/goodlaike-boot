package com.goodlaike.framework.tools.utils.aliyun;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;

public class SmsUtil {

	static DefaultProfile profile;
	
	static String signName;

	static Map<String, SmsType> smsTemplateMap;

	static {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration();
			config.setEncoding("utf-8");
			config.load("ali-sms-config.properties");
			signName = config.getString("sms.signName");

			String regionId = config.getString("sms.regionId");
			String accessKeyId = config.getString("sms.accessKeyId");
			String secret = config.getString("sms.secret");
			String domain = config.getString("sms.domain");
			String endpointName = config.getString("sms.endpointName");
			String templates = config.getString("sms.templates");

			if (!StringUtils.isEmpty(templates)) {
				List<SmsType> smsTypeList = JSON.parseArray(templates, SmsType.class);
				smsTemplateMap = smsTypeList.stream().collect(Collectors.toMap(SmsType::getName, Function.identity()));
			} else {
				System.err.println("ali-sms-config.properties 短信模板配置缺失");
			}

			profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
			DefaultProfile.addEndpoint(endpointName, regionId, "Sms", domain);

		} catch (ConfigurationException e) {
			System.err.println("缺少SMS相关配置文件 ali-sms-config.properties，短信服务启动失败");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建6随机验证码
	 * 
	 * @return int
	 */
	public static int createVeriCode() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}

	/**
	 * 发送短信
	 * 
	 * @param aliSmsType
	 *            阿里短信类型
	 * @param recNum
	 *            接收号码，多个号码用 , 间隔
	 * @param params
	 *            短信模板中用到的参数
	 * @throws ClientException
	 */
	public static void sendSms(AliSmsType aliSmsType, String recNum, String... params) throws ClientException {
		Assert.notNull(aliSmsType);
		Assert.hasText(recNum);
		SmsType smsType;
		Assert.notNull((smsType = smsTemplateMap.get(aliSmsType.name().toLowerCase())));
		long holderParamCount = smsType.getHolders().stream().filter(h -> StringUtils.isEmpty(h.getValue())).count();
		Assert.isTrue(holderParamCount == params.length, "参数个数不正确，与短信模板不匹配，请检查");
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendSmsRequest request = new SingleSendSmsRequest();
		try {
			request.setSignName(signName);
			request.setTemplateCode(smsType.getTemplateCode());
			Map<String, String> paramMap = new HashMap<>();

			TemplateHolder holder;
			for (int i = 0; i < smsType.getHolders().size(); i++) {
				holder = smsType.getHolders().get(i);
				if (StringUtils.isEmpty(holder.getValue())) {
					paramMap.put(holder.getName(), params[i]);
				} else {
					paramMap.put(holder.getName(), holder.getValue());
				}
			}
			request.setParamString(JSON.toJSONString(paramMap));
			request.setRecNum(recNum);
			client.doAction(request);
			// SingleSendSmsResponse httpResponse =
			// client.getAcsResponse(request);
			// System.out.println(httpResponse);
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	public static enum AliSmsType {
		/**
		 * params: code、product
		 */
		REGISTER,
		/**
		 * params: customer
		 */
		TEST;
	}

	public static class TemplateHolder implements Serializable {

		private static final long serialVersionUID = 1L;

		private String name;
		private String value;

		/**
		 * @return the
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "TemplateHolder [name=" + name + ", value=" + value + "]";
		}

	}

	public static class SmsType implements Serializable {

		private static final long serialVersionUID = 1L;

		private String name;
		private String templateCode;
		private List<TemplateHolder> holders;

		/**
		 * @return the
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the
		 */
		public String getTemplateCode() {
			return templateCode;
		}

		/**
		 * @param templateCode
		 *            the templateCode to set
		 */
		public void setTemplateCode(String templateCode) {
			this.templateCode = templateCode;
		}

		/**
		 * @return the
		 */
		public List<TemplateHolder> getHolders() {
			return holders;
		}

		/**
		 * @param holders
		 *            the holders to set
		 */
		public void setHolders(List<TemplateHolder> holders) {
			this.holders = holders;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "SmsType [name=" + name + ", templateCode=" + templateCode + ", holders=" + holders + "]";
		}

	}
}
