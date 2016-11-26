package com.goodlaike.business.fileupload.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

/**
 * 文件上传配置类
 * 
 * @author Jail Hu
 */
@Configuration
@PropertySource(value = { "classpath:fileupload-config.properties" })
public class FileUploadConfig { // implements
                                // ApplicationListener<ContextRefreshedEvent>
	@Autowired
	Environment env;

	private String fileSavePath;

	/**
	 * 获得图片保存路径
	 * @return
	 * @author Jail Hu
	 */
	public String getFileSavePath() {
		if (this.fileSavePath == null) {
			String saveDirectory = env.getProperty("file.save.directory");
			Assert.hasText(saveDirectory, "上传文件未设置保存路径");
			saveDirectory = saveDirectory.replaceAll("[\\/]", File.separator);
			if (!saveDirectory.contains(":")) {
				if (!saveDirectory.startsWith(File.separator)) {
					saveDirectory = File.separator + saveDirectory;
				}
				String classPath = this.getClass().getClassLoader().getResource("").getPath();
				saveDirectory = classPath.replace("/WEB-INF/classes/", "") + saveDirectory;
			}

			File d = new File(saveDirectory);
			if (!d.exists()) {
				d.mkdirs();
			}
			System.out.println("============" + saveDirectory);
			this.fileSavePath = saveDirectory;
		}
		return this.fileSavePath;
	}

	public int getFileMaxSize() {
		return env.getProperty("file.max.size", Integer.class);
	}

	/*
	 * private ApplicationContext applicationContext;
	 * 
	 * 
	 * @Override public void onApplicationEvent(ContextRefreshedEvent arg0) { if
	 * (this.applicationContext == null) { this.applicationContext =
	 * arg0.getApplicationContext(); } }
	 */
}
