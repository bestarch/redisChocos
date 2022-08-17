package com.bestarch.demo.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestarch.demo.domain.Product;
import com.bestarch.demo.domain.UserProfile;
import com.bestarch.demo.repository.UserProfileCrudRepository;
import com.bestarch.demo.service.RedisCommerceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redislabs.lettusearch.Document;
import com.redislabs.lettusearch.RediSearchCommands;
import com.redislabs.lettusearch.SearchOptions;
import com.redislabs.lettusearch.SearchOptions.Limit;
import com.redislabs.lettusearch.SearchOptions.SortBy;
import com.redislabs.lettusearch.SearchResults;
import com.redislabs.lettusearch.StatefulRediSearchConnection;
//import com.redislabs.modules.rejson.JReJSON;

@Service
public class RedisCommerceServiceImpl extends RedisCommerceService {
	
	//@Autowired
	//private JReJSON jreJSON; 
	
	private final static String QUERY = "*";
	//private final static String AGGREGATE_QUERY = "@appointmentDateTime:[%d %d]";
	
	@Autowired
	private StatefulRediSearchConnection<String, String> connection;
	
	@Autowired
	private UserProfileCrudRepository userProfileCrudRepository;
	
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Product> getProducts(int offset, int page, String searchParam) {
		List<Product> products = new ArrayList<>();
		RediSearchCommands<String, String> commands = connection.sync();
		SortBy<String> sortBy = SortBy.<String>builder().field("productName").build();
		Limit limit = SearchOptions.Limit.builder().offset(offset).num(page).build();
		SearchOptions<String> searchOptions = SearchOptions
				.<String>builder()
				.sortBy(sortBy)
				.limit(limit)
				.build();
		
//		SearchResults<String, String> results = commands.search("idx-createdTime", QUERY, searchOptions);
//		
//		Product p = null;
//		for (Document<String, String> doc : results) {
//			Set<Entry<String, String>> entrySet = doc.entrySet();
//			Iterator<Entry<String, String>> iter = entrySet.iterator();
//			while (iter.hasNext()) {
//				Entry<String, String> en = iter.next();
//				if ("$".equals(en.getKey())) {
//					try {
//						p = objectMapper.readValue(en.getValue(), Product.class);
//						products.add(p);
//					} catch (Exception e) {
//						e.printStackTrace();
//						return new ArrayList<>();
//					}
//				}
//			}
//		}
		Product p = new Product();
		p.setLocation("/assets/custom/images/5star_3d.png");
		p.setDescription("hfwdfg dsgfuidsgf dsufg iugfd s");
		p.setCategory("ITEMMMM");
		p.setPrice(3.0);
		p.setProductId("yfg823u4");
		p.setProductName("Cadbury's 5 Star chocolate");
		p.setSku("SKU587323");
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		products.add(p);
		return products;
	}

	@Override
	public void saveUserProfile(UserProfile userProfile) {
		String username = utility.getUsername();
		userProfile.setUsername(username);
		userProfileCrudRepository.save(userProfile);
		
	}

	@Override
	public Optional<UserProfile> getUserProfile(String username) {
		return userProfileCrudRepository.findById(username);
	}
	
}
