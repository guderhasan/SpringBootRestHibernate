package com.bilgeadam.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bilgeadam.springbootthymeleaf.model.Ogretmen;
import com.bilgeadam.springbootthymeleaf.service.OgretmenService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(path = { "ogretmen", "ogretmen.html" })
public class OgretmenController {
	private OgretmenService ogretmenService;

	@GetMapping(path = "")
	public ModelAndView getAll() {
		ModelAndView ogretmenView = new ModelAndView("ogretmen");
		ogretmenView.addObject("liste", ogretmenService.getAll());
		return ogretmenView;
	}

	@GetMapping(path = "ekle")
	public ModelAndView kaydet() {
		ModelAndView ogretmenView = new ModelAndView("ogretmen_ekle");
		ogretmenView.addObject("ogretmen", new Ogretmen());
		return ogretmenView;
	}

	@GetMapping(path = "guncelle")
	public ModelAndView guncelle(@RequestParam(name = "id") Long id) {
		ModelAndView ogretmenView = new ModelAndView("ogretmen_guncelle");
		Ogretmen ogretmen = ogretmenService.getById(id);
		ogretmenView.addObject("ogretmen", ogretmen);
		return ogretmenView;
	}

	@PostMapping(path = "guncelle")
	public ModelAndView guncellePost(Ogretmen ogretmen) {
		ogretmenService.update(ogretmen);
		return new ModelAndView("redirect:/ogretmen");
	}

	@GetMapping(path = "detay")
	public ModelAndView detay(@RequestParam(name = "id") Long id) {
		ModelAndView ogretmenView = new ModelAndView("ogretmen_detay");
		Ogretmen ogretmen = ogretmenService.getById(id);
		ogretmenView.addObject("ogretmen", ogretmen);
		return ogretmenView;
	}

	@PostMapping(path = "sil")
	public ModelAndView sil(Long OGR_ID) {
		ogretmenService.deleteById(OGR_ID);
		return new ModelAndView("redirect:/ogretmen");
	}

	@PostMapping(path = "ekle")
	public ModelAndView kaydetPost(Ogretmen ogretmen) {
		ogretmenService.save(ogretmen);
		return new ModelAndView("redirect:/ogretmen");
	}
}