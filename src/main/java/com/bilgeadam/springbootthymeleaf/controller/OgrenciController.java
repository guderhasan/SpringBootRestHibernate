package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Ogrenci;
import com.bilgeadam.springbootthymeleaf.service.OgrenciService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(path = { "ogrenci", "ogrenci.html" })
public class OgrenciController {
	private OgrenciService ogrenciService;

	@GetMapping(path = "")
	public ModelAndView getAll() {
		ModelAndView ogrenciView = new ModelAndView("ogrenci");
		ogrenciView.addObject("liste", ogrenciService.getAll());
		return ogrenciView;
	}

	@GetMapping(path = "ekle")
	public ModelAndView kaydet() {
		ModelAndView ogrenciView = new ModelAndView("ogrenci_ekle");
		ogrenciView.addObject("ogrenci", new Ogrenci());
		return ogrenciView;
	}

	@PostMapping(path = "ekle")
	public ModelAndView kaydetPost(Ogrenci ogrenci) {
		ogrenciService.save(ogrenci);
		return new ModelAndView("redirect:/ogrenci");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id) {
		ModelAndView ogrenciView = new ModelAndView("ogrenci_guncelle");
		Ogrenci ogrenci = ogrenciService.getById(id);
		ogrenciView.addObject("ogrenci", ogrenci);
		return ogrenciView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(Ogrenci ogrenci) {
		ogrenciService.update(ogrenci);
		return new ModelAndView("redirect:/ogrenci");
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id) {
		ModelAndView ogrenciView = new ModelAndView("ogrenci_detay");
		Ogrenci ogrenci = ogrenciService.getById(id);
		ogrenciView.addObject("ogrenci", ogrenci);
		return ogrenciView;
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(Long OGR_ID) {
		ogrenciService.deleteById(OGR_ID);
		return new ModelAndView("redirect:/ogrenci");
	}

}