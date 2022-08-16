package com.bestarch.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bestarch.demo.domain.Product;
import com.bestarch.demo.domain.UserProfile;
import com.bestarch.demo.util.RedisCommerceUtil;

@Service
public abstract class RedisCommerceService {
	
	@Autowired
	protected RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	protected RedisCommerceUtil utility;
	
	public abstract List<Product> getProducts(int offset, int page, String searchParam);
	
	public abstract void saveUserProfile(UserProfile userProfile);

	public abstract Optional<UserProfile> getUserProfile(String username);

}
