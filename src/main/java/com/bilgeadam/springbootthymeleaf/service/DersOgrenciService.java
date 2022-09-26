package com.bilgeadam.springbootthymeleaf.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.bilgeadam.springbootthymeleaf.model.DersOgrenci;
import com.bilgeadam.springbootthymeleaf.repo.DersOgrenciRepository;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class DersOgrenciService {
	private DersOgrenciRepository jpaRepo;

	public List<DersOgrenci> getDersOgrenciByOgrenciName(String name) {
		return jpaRepo.findAllDersOgrenciByogrenci_NAMELike("%" + name + "%");
	}

	public List<DersOgrenci> getDersOgrenciByOgrenciId(Long id) {
		return jpaRepo.findAllDersOgrenciByogrenci_ID(id);
	}

	public List<DersOgrenci> getDersOgrenciByDersId(Long id) {
		return jpaRepo.findAllDersOgrenciByders_ID(id);
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

	public boolean save(DersOgrenci dersOgrenci) {
		try {
			return jpaRepo.save(dersOgrenci) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public DersOgrenci getById(Long id) {
		return jpaRepo.findById(id).get();
	}

	public List<DersOgrenci> getAll() {
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public DersOgrenci update(DersOgrenci dersogrenci) {
		boolean exists = false;
		try {
			exists = jpaRepo.existsById(dersogrenci.getID());
		} catch (Exception e) {
			exists = false;
		}
		if (exists) {
			return jpaRepo.save(dersogrenci);
		} else {
			return null;
		}
	}
}