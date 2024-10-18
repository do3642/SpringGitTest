package com.example.hosting.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.hosting.domain.Product;
import com.example.hosting.repository.ProductRepository;
import com.example.hosting.service.S3Service;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
	
	private final S3Service s3Service;
	private final ProductRepository productRepository;
	
	
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
	
	@PostMapping("/product")
	public String insertProduct(Product product) {
		productRepository.save(product);
		
		return "redirect:/";
	}
	
	//선생님은 그냥 MainController에서 작업함
	@GetMapping({"","/"})
	public String showProduct(Model model) {
		
		List<Product> productList = productRepository.findAll();
		model.addAttribute("products", productList);
		
		return "index";
	}
	
}
