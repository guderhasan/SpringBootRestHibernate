package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Konu;
import com.bilgeadam.springbootthymeleaf.service.KonuService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(path = { "konu", "konu.html" })
public class KonuController {
	private KonuService konuService;

	@GetMapping(path = "")
	public ModelAndView getAll() {

		ModelAndView konuView = new ModelAndView("konu");
		konuView.addObject("liste", konuService.getAll());
		return konuView;
	}

	@GetMapping(path = "ekle")
	public ModelAndView kaydet() {
		ModelAndView konuView = new ModelAndView("konu_ekle");
		konuView.addObject("konu", new Konu());
		return konuView;
	}

	@PostMapping(path = "ekle")
	public ModelAndView kaydetPost(Konu konu) {
		konuService.save(konu);

		return new ModelAndView("redirect:/konu");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id) {
		ModelAndView konuView = new ModelAndView("konu_guncelle");
		Konu konu = konuService.getById(id);
		konuView.addObject("konu", konu);
		return konuView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(Konu konu) {
		konuService.update(konu);
		return new ModelAndView("redirect:/konu");
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id) {
		ModelAndView konuView = new ModelAndView("konu_detay");
		Konu konu = konuService.getById(id);
		konuView.addObject("konu", konu);
		return konuView;
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(Long KONU_ID) {
		konuService.deleteById(KONU_ID);
		return new ModelAndView("redirect:/konu");
	}

}