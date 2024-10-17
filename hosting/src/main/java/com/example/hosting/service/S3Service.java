package com.example.hosting.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor //final키워드 들어있는 변수만 받아주는 생성자
public class S3Service {
	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;
	private final S3Presigner s3Presigner;
	
	public String createPresignedUrl(String path) {
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
															.bucket(bucket) // 버캣명
															.key(path) // 경로와 파일명
															.build();
		
		
		PutObjectPresignRequest putObjectPresignRequest = 
				PutObjectPresignRequest.builder()
				.signatureDuration(Duration.ofMinutes(5)) // 요청받은 url의 유효기간
				.putObjectRequest(putObjectRequest)
				.build();
		
		return s3Presigner.presignPutObject(putObjectPresignRequest).url().toString();
		
	}
}
