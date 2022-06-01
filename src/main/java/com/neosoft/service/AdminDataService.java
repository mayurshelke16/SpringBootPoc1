package com.neosoft.service;

import java.util.List;

import javax.validation.Valid;

import com.neosoft.entity.AdminData;

public interface AdminDataService {

	AdminData addAdminDetails(@Valid AdminData admin);

	AdminData updateAdminDetails(long id, @Valid AdminData adminData);

	AdminData getAdminName(String name);

	AdminData getBySurname(String surname);

	List<AdminData> getByPincode(String pincode);

	List<AdminData> sortDob();

	List<AdminData> sortDoj();

	void deleteAdmin(long id);

	void softDeleteAdmin(long id);

}
