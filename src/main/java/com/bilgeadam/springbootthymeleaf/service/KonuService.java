package com.bilgeadam.springbootthymeleaf.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bilgeadam.springbootthymeleaf.model.Konu;
import com.bilgeadam.springbootthymeleaf.repo.KonuRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "KonuServisi")
public class KonuService {
	private Logger myLogger = LoggerFactory.getLogger("KonuServisi");

	@lombok.NonNull
	private KonuRepository jpaRepo;

	public boolean deleteById(Long id) {
		try {
			jpaRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(Konu konu) {
		try {
			return jpaRepo.save(konu) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Konu> findByName(String name) {
		return jpaRepo.findAllByNAMELikeOrderByIDDesc("%" + name + "%");
	}

	public Konu getById(Long id) {
		myLogger.info("--------> konu getbyid yapılıyor");
		myLogger.trace("--------> konu getbyid yapılıyor id = " + id);
		return jpaRepo.findById(id).get();
	}

	public List<Konu> getAll() {
		log.info("Lombok tarafından oluşturuldu");
		return jpaRepo.findAll(Sort.by("ID").descending());
	}

	public Konu update(Konu konu) {
		boolean exists = false;
		try {
			exists = jpaRepo.existsById(konu.getID());
		} catch (Exception e) {
			exists = false;
		}
		if (exists) {
			return jpaRepo.save(konu);
		} else {
			return null;
		}
	}
}