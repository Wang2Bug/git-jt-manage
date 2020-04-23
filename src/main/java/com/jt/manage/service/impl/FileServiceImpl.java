package com.jt.manage.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Service
public class FileServiceImpl implements FileService
{
	private String localDirPath = "E:/Program/jt-upload";
	private String localUrlPath = "http://image.jt.com/";
	
	public PicUploadResult uploadFile(MultipartFile uploadFile) 
	{
		PicUploadResult result = new PicUploadResult();
		//1.判断文件类型 abc.jpg
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();
		//2.使用正则表达式判断
		if(!fileName.matches("^.*\\.(jpg|png|gif)$")) 
		{
			//表示不是图片
			result.setError(1);
			return result;
		}
		
		//3.判断是否为恶意程序
		try 
		{
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			
			//判断是否为图片
			if(width == 0 || height == 0) 
			{
				result.setError(1);
				return result;
			}
			//生成日期文件夹
			String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			//判断文件夹是否存在
			String dirFilePath = localDirPath+"/"+dateDir;
			File dirFile = new File(dirFilePath);
			
			if(!dirFile.exists()) 
			{
				//如果文件夹不存在，则新建
				dirFile.mkdirs();
			}
			
			
			//生成UUID
			String uuid = UUID.randomUUID().toString().replace("-", "");
			//生成随机数
			int randomNum = new Random().nextInt(1000);
			//截取文件后缀
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			//获取文件名称
			String imageFileName = uuid + randomNum + fileType;
			//E:/jt-upload/yyyy/MM/dd/uuidxxx.jpg
			uploadFile.transferTo(new File(dirFilePath+"/"+imageFileName));
			
			//处理返回值
			result.setWidth(width+"");
			result.setHeight(height+"");
			
			//封装url http://image.jt.com/yyyy/MM/dd/abc.jpg
			String url = localUrlPath + dateDir + "/" +imageFileName;
			result.setUrl(url);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			result.setError(1);
			return result;
		}
		return result;
	}
	
}
