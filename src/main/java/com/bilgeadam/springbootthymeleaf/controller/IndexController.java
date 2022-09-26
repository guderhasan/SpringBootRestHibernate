package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = { "index", "index.html" })
public class IndexController {

	@GetMapping
	public ModelAndView index() {
		ModelAndView indexView = new ModelAndView("index");
		indexView.addObject("username", "hasan");
		return indexView;
	}
}
