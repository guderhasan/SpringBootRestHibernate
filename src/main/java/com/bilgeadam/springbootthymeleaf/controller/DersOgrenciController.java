package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.DersOgrenci;
import com.bilgeadam.springbootthymeleaf.service.DersOgrenciService;
import com.bilgeadam.springbootthymeleaf.service.DersService;
import com.bilgeadam.springbootthymeleaf.service.OgrenciService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(path = { "dersogrenci", "dersogrenci.html" })
public class DersOgrenciController {

	private DersOgrenciService dersogrenciService;
	private DersService dersService;
	private OgrenciService ogrenciService;

	@GetMapping(path = "")
	public ModelAndView getAll() {
		ModelAndView dersogrenciView = new ModelAndView("dersogrenci");
		dersogrenciView.addObject("liste", dersogrenciService.getAll());
		return dersogrenciView;
	}

	@GetMapping(path = "ekle")
	public ModelAndView kaydet() {
		ModelAndView dersogrenciView = new ModelAndView("dersogrenci_ekle");
		dersogrenciView.addObject("dersogrenci", new DersOgrenci());
		dersogrenciView.addObject("ders_list", dersService.getAll());
		dersogrenciView.addObject("ogrenci_list", ogrenciService.getAll());
		return dersogrenciView;
	}

	@PostMapping(path = "ekle")
	public ModelAndView kaydetPost(DersOgrenci dersogrenci) {
		dersogrenciService.save(dersogrenci);

		return new ModelAndView("redirect:/dersogrenci");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id) {
		ModelAndView dersogrenciView = new ModelAndView("dersogrenci_guncelle");
		DersOgrenci dersogrenci = dersogrenciService.getById(id);
		dersogrenciView.addObject("dersogrenci", dersogrenci);
		dersogrenciView.addObject("ders_list", dersService.getAll());
		dersogrenciView.addObject("ogrenci_list", ogrenciService.getAll());
		return dersogrenciView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(DersOgrenci dersogrenci) {
		dersogrenciService.update(dersogrenci);
		return new ModelAndView("redirect:/dersogrenci");
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id) {
		ModelAndView dersogrenciView = new ModelAndView("dersogrenci_detay");
		DersOgrenci dersogrenci = dersogrenciService.getById(id);
		dersogrenciView.addObject("dersogrenci", dersogrenci);
		return dersogrenciView;
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(Long dersOgrenci_ID) {
		dersogrenciService.deleteById(dersOgrenci_ID);
		return new ModelAndView("redirect:/dersogrenci");
	}

}