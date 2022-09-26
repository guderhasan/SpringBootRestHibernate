package com.bilgeadam.springbootthymeleaf.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleaf.model.Ogrenci;
import com.bilgeadam.springbootthymeleaf.repo.OgrenciRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OgrenciService {
	private OgrenciRepository jpaRepo;

	public boolean deleteById(Long id) {
		try {
			jpaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(Ogrenci ogrenci) {
		try {
			return jpaRepo.save(ogrenci) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Ogrenci> findByName(String name) {
		return jpaRepo.findAllByNAMELikeOrderByIDDesc("%" + name + "%");
	}

	public Ogrenci getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<Ogrenci> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public Ogrenci update(Ogrenci ogrenci) {
		boolean exists = false;
		try {
			exists = jpaRepo.existsById(ogrenci.getID());
		} catch (Exception e) {
			exists = false;
		}
		if (exists) {
			return jpaRepo.save(ogrenci);
		} else {
			return null;
		}
	}
}