package com.jt.manage.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Controller
public class FileController 
{
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/file")
	public String file(MultipartFile fileImage) throws IllegalStateException, IOException 
	{
		//1.获取文件名称 abc.jpg
		String fileName = fileImage.getOriginalFilename();
		
		//2.定义文件夹
		String filePath = "E:/Program/jt-upload";
		
		//3.判断文件加是否存在
		File imageFile = new File(filePath);
		
		if(!imageFile.exists()) 
		{
			//新建文件夹
			imageFile.mkdirs();
		}
		
		//4.实现文件上传
		fileImage.transferTo(new File(imageFile+"/"+fileName));
		
		System.out.println("文件上传成功！！");
		return "redirect:/file.jsp";
	}
	
	//实现业务的文件上传
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult uploadFile(MultipartFile uploadFile) 
	{
		return fileService.uploadFile(uploadFile);
	}
}
