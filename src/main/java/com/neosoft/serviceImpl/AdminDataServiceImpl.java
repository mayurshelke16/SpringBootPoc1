package com.neosoft.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.entity.AdminData;
import com.neosoft.exception.ResourceNotFoundException;
import com.neosoft.repository.AdminDataRepository;
import com.neosoft.service.AdminDataService;
import com.neosoft.utils.Constant;

@Service
public class AdminDataServiceImpl implements AdminDataService {

	@Autowired
	private AdminDataRepository repository;

	@Override
	public AdminData addAdminDetails(@Valid AdminData admin) {

		admin.setDeleted(Constant.IS_DELETED_FALSE);
		AdminData adminData = repository.save(admin);
		return adminData;
	}

	@Override
	public AdminData updateAdminDetails(long id, @Valid AdminData adminData) {
		AdminData data = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with id :" + id));

		data.setName(adminData.getName());
		data.setSurname(adminData.getSurname());
		data.setAddress(adminData.getAddress());
		data.setGender(adminData.getGender());
		data.setAge(adminData.getAge());
		data.setPincode(adminData.getPincode());
		data.setDOB(adminData.getDOB());
		data.setDOJ(adminData.getDOJ());

		return repository.save(data);
	}

	@Override
	public AdminData getAdminName(String name) {
		Optional<AdminData> adminData = repository.findByName(name);
		if (adminData.isEmpty()) {
			throw new ResourceNotFoundException("Admin not found with name :" + name);
		}
		AdminData data = adminData.get();
		return data;
	}

	@Override
	public AdminData getBySurname(String surname) {
		AdminData adminData = repository.findBySurname(surname);
		return adminData;
	}

	@Override
	public List<AdminData> getByPincode(String pincode) {
		Optional<List<AdminData>> adminData = repository.findByPincode(pincode);
		if (!adminData.isPresent()) {
			throw new ResourceNotFoundException("Admin not found with name :" + pincode);
		}
		List<AdminData> data = adminData.get();
		return data;
	}

	@Override
	public List<AdminData> sortDob() {
		List<AdminData> admiList = repository.findAllByOrderByDOBAsc();
		return admiList;
	}

	@Override
	public List<AdminData> sortDoj() {
		List<AdminData> admiList = repository.findAllByOrderByDOJDesc();
		return admiList;
	}

	@Override
	public void deleteAdmin(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found with id :" + id));
		repository.deleteById(id);

	}

	@Override
	public void softDeleteAdmin(long id) {
		AdminData data = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with id :" + id));
		data.setDeleted(Constant.IS_DELETED_TRUE);
		repository.save(data);

	}

}
