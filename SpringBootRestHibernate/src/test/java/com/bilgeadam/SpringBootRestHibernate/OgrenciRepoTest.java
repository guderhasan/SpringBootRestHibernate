package com.bilgeadam.SpringBootRestHibernate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bilgeadam.SpringBootRestHibernate.jparepository.OgrenciRepository;
import com.bilgeadam.SpringBootRestHibernate.model.Ogrenci;

@DataJpaTest
// ben gerçek database 'de çalışmak istiyorum
@AutoConfigureTestDatabase(replace = Replace.NONE)
// junit4 olursa
// @RunWith(SpringRunner.class)
public class OgrenciRepoTest
{
	@Autowired
	public OgrenciRepository repo;

	@Test
	@RepeatedTest(value = 3)
	// @Rollback(value = false)
	public void saveTest()
	{
		Ogrenci ogrenci = new Ogrenci("Zehra", 765L, 3L);
		repo.save(ogrenci);
		Ogrenci result = repo.findByOgrenciName("Zehra").get(0);
		Assertions.assertEquals(result.getOGR_NUMBER(), ogrenci.getOGR_NUMBER());
	}
}