package com.bilgeadam.springbootthymeleaf.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleaf.model.Ogretmen;
import com.bilgeadam.springbootthymeleaf.repo.OgretmenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OgretmenService {
	private OgretmenRepository jpaRepo;

	public List<Ogretmen> findByName(String name) {
		return jpaRepo.findAllByNAMELikeOrderByIDDesc("%" + name + "%");
	}

	public boolean deleteById(Long id) {
		try {
			jpaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(Ogretmen ogretmen) {
		try {
			return jpaRepo.save(ogretmen) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Ogretmen update(Ogretmen ogretmen) {
		boolean exists = false;
		try {
			exists = jpaRepo.existsById(ogretmen.getID());
		} catch (Exception e) {
			exists = false;
		}
		if (exists) {
			return jpaRepo.save(ogretmen);
		} else {
			return null;
		}
	}

	public Ogretmen getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<Ogretmen> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}
}