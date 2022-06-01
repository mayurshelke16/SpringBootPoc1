package com.neosoft.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Admin")
public class AdminData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = " Name is mandatory")
	private String name;

	@NotBlank(message = " Surname is mandatory")
	private String surname;

	@NotBlank(message = " Address is mandatory")
	private String address;

	@NotBlank(message = " Gender is mandatory")
	private String gender;

	@NotBlank
	private int age;

	@NotBlank
	private String pincode;

	@NotBlank
	private Date DOB;

	@NotBlank
	private Date DOJ;

	@Column(name = "is_deleted")
	private boolean isDeleted;

}
