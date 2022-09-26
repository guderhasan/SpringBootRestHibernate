package com.bilgeadam.SpringBootRestHibernate.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.SpringBootRestHibernate.jparepository.OgretmenRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ogretmen;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(path = "/person")
@RestController
@Slf4j()
public class PersonController {

	@Autowired
	OgretmenRepository repo;

	Logger logger = LoggerFactory.getLogger(PersonController.class);

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Ogretmen>> getById(@PathVariable(value = "id") Long id) {
		Optional<Ogretmen> person = repo.findById(id);
		return ResponseEntity.ok(person);

	}

	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insert(@RequestBody Ogretmen ogr, @RequestHeader(value = "username") String username,
			@RequestHeader(value = "password") String password) {
		logger.info(username);
		repo.save(ogr);
		return ResponseEntity.status(HttpStatus.CREATED).body("Kayıt Başarılı");

	}

}
