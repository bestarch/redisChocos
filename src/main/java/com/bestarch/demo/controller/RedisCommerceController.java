package com.bestarch.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bestarch.demo.domain.Product;
import com.bestarch.demo.domain.UserProfile;
import com.bestarch.demo.service.RedisCommerceService;
import com.bestarch.demo.util.RedisCommerceUtil;

@Controller
public class RedisCommerceController {
	
	@Autowired
	private RedisCommerceService redisCommerceService;
	
	@Autowired
	private RedisCommerceUtil utility;
	
	@GetMapping(value = {"/", "/products"})
	public ModelAndView getProducts(@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "15") int page, @RequestParam(defaultValue = "*") String searchParam) {
		List<Product> products = redisCommerceService.getProducts(offset, page, searchParam);
		ModelAndView mv = new ModelAndView("products");
        mv.addObject("products", products);
        mv.addObject("searchParam", searchParam);
		return mv;
	}
	
	@PostMapping(value = "/profile", consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
	public String saveUserProfile(@ModelAttribute UserProfile userProfile, BindingResult errors, Model model) {
		redisCommerceService.saveUserProfile(userProfile);
		return "redirect:/appointments";
	}
	
	@GetMapping(value = {"/profile"})
	public ModelAndView getUserProfile() {
		String username = utility.getUsername();
		Optional<UserProfile> userProfile = redisCommerceService.getUserProfile(username);
		ModelAndView mv = new ModelAndView("profile");
		if (userProfile.isPresent()) {
			mv.addObject("userProfile", userProfile.get());
		} else {
			UserProfile profile = new UserProfile();
			profile.setUsername(username);
			mv.addObject("userProfile", profile);
		}
		return mv;
	}
	
	@GetMapping(value = {"/logout"})
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}