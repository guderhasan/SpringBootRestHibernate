package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Ders;
import com.bilgeadam.springbootthymeleaf.service.DersService;
import com.bilgeadam.springbootthymeleaf.service.KonuService;
import com.bilgeadam.springbootthymeleaf.service.OgretmenService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(path = { "ders", "ders.html" })
public class DersController {
	private DersService dersService;

	private OgretmenService ogretmenService;

	private KonuService konuService;

	@GetMapping(path = "")
	public ModelAndView getAll() {

		ModelAndView dersView = new ModelAndView("ders");
		dersView.addObject("liste", dersService.getAll());
		return dersView;
	}

	@GetMapping(path = "ekle")
	public ModelAndView kaydet() {
		ModelAndView dersView = new ModelAndView("ders_ekle");
		dersView.addObject("ders", new Ders());
		dersView.addObject("ogretmen_list", ogretmenService.getAll());
		dersView.addObject("konu_list", konuService.getAll());
		return dersView;
	}

	@PostMapping(path = "ekle")
	public ModelAndView kaydetPost(Ders ders) {
		dersService.save(ders);
		return new ModelAndView("redirect:/ders");
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id) {
		ModelAndView dersView = new ModelAndView("ders_guncelle");
		Ders ders = dersService.getById(id);
		dersView.addObject("ders", ders);
		dersView.addObject("ogretmen_list", ogretmenService.getAll());
		dersView.addObject("konu_list", konuService.getAll());

		return dersView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(Ders ders) {
		dersService.update(ders);
		return new ModelAndView("redirect:/ders");
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id) {
		ModelAndView dersView = new ModelAndView("ders_detay");
		Ders ders = dersService.getById(id);
		dersView.addObject("ders", ders);
		return dersView;
	}

	@PostMapping(path = "sil")

	public ModelAndView sil(Long DERS_ID) {
		dersService.deleteById(DERS_ID);
		return new ModelAndView("redirect:/ders");
	}

}