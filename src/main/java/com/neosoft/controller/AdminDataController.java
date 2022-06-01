package com.neosoft.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.entity.AdminData;
import com.neosoft.service.AdminDataService;

@RestController
@RequestMapping("/admin")
public class AdminDataController {

	@Autowired
	private AdminDataService adminDataService;

	@PostMapping("/add")
	public ResponseEntity<AdminData> addAdmin(@Valid @RequestBody AdminData admin) {

		AdminData adminData = adminDataService.addAdminDetails(admin);
		return new ResponseEntity<AdminData>(adminData, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdminData> updateAdmin(@PathVariable long id, @Valid @RequestBody AdminData adminData) {
		AdminData data = adminDataService.updateAdminDetails(id, adminData);
		return new ResponseEntity<AdminData>(data, HttpStatus.OK);

	}

	@GetMapping("/name/{name}")
	public ResponseEntity<AdminData> getAdminByName(@PathVariable String name) {
		AdminData adminData = adminDataService.getAdminName(name);
		return new ResponseEntity<AdminData>(adminData, HttpStatus.FOUND);
	}

	@GetMapping("/surname/{surname}")
	public ResponseEntity<AdminData> getAdminBySurname(@PathVariable String surname) {
		AdminData adminData = adminDataService.getBySurname(surname);
		return new ResponseEntity<AdminData>(adminData, HttpStatus.OK);
	}

	@GetMapping("/{pincode}")
	public ResponseEntity<List<AdminData>> getAdminByPincode(@PathVariable String pincode) {
		List<AdminData> adminData = adminDataService.getByPincode(pincode);
		return new ResponseEntity<List<AdminData>>(adminData, HttpStatus.OK);
	}

	@GetMapping("/sortdob")
	public ResponseEntity<List<AdminData>> SortDateOfBirth() {
		List<AdminData> sortdata = adminDataService.sortDob();

		return new ResponseEntity<List<AdminData>>(sortdata, HttpStatus.OK);
	}

	@GetMapping("/sortdoj")
	public ResponseEntity<List<AdminData>> SortDateOfJion() {
		List<AdminData> sortdata = adminDataService.sortDoj();

		return new ResponseEntity<List<AdminData>>(sortdata, HttpStatus.OK);
	}

	@DeleteMapping("/hard/{id}")
	public ResponseEntity<String> hardDeleteAdminById(@PathVariable long id) {
		adminDataService.deleteAdmin(id);
		return new ResponseEntity<String>("Admin is permentant delete", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> softDeleteAdminById(@PathVariable long id) {
		adminDataService.softDeleteAdmin(id);
		return new ResponseEntity<String>("Admin is delete", HttpStatus.OK);
	}
	
}