package com.neosoft;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.neosoft.entity.AdminData;
import com.neosoft.repository.AdminDataRepository;
import com.neosoft.utils.Constant;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AdminDataTest {

	@Autowired
	private AdminDataRepository repository;

	@Test
	@Order(1)
	public void testCreate() {
		AdminData data = new AdminData();
		data.setId(6L);
		data.setName("Mayur");
		data.setSurname("sutar");
		data.setAddress("pune");
		data.setAge(18);
		data.setGender("Male");
		data.setPincode("410506");
		repository.save(data);
		assertNotNull(repository.findById(6L).get());
		assertThat(data.getName()).isNotEqualTo("ram");

	}

	@Test
	@Order(2)
	public void testUpdate() {
		AdminData adminData = repository.findById(6L).get();
		adminData.setName("Mayur");
		adminData.setSurname("sutar");
		adminData.setAddress("pune");
		adminData.setAge(18);
		adminData.setPincode("410506");
		adminData.setGender("Male");
		repository.save(adminData);
		assertEquals("pune", repository.findById(6L).get().getAddress());
	}

	@Test
	@Order(3)
	public void testSurnameSearch() {
		AdminData data = repository.findBySurname("sutar");
		assertNotEquals("karan", data.getSurname());
	}

	@Test
	@Order(5)
	public void testSoftDelete() {
		AdminData data = repository.findById(7L).get();
		data.setDeleted(Constant.IS_DELETED_TRUE);
		repository.save(data);
		assertThat(repository.existsById(7L)).isTrue();
	}
@Test
@Order(6)
	public void testHardDelete()
	{
		repository.deleteById(1L);
	assertThat(repository.existsById(1L)).isTrue();
	}
}
