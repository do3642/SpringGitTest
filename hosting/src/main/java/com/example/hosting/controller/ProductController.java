package com.example.hosting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hosting.service.S3Service;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final S3Service s3Service;
	
	
	@GetMapping("/product")
	public String product() {
		return "insertProduct";
	}
	
	@GetMapping("/presigned-url")
	@ResponseBody
	public String  getURL(String filename) {
		String presignedUrl = s3Service.createPresignedUrl("img/" + filename);
		return presignedUrl;
	}
}
