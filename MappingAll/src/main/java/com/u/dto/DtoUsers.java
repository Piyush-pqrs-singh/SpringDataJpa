package com.u.dto;

import java.util.List;

import lombok.Data;

@Data
public class DtoUsers {

    private String city;
	
	private String state;
	
	private String country;
	
	private String zipCode;
	
    private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String contactNo;
	
	private Long stateId;
	private String stateName;
	
	private Long cityId;
	private String cityName;
	
	private Long pcId;
	private Integer pcContact;
	private String principleCustName;	
	private String pcAddress;
	
	private Long custId;
	private Integer custContact;
	private String custName;	
	private String custAddress;
	
	private Long id;
	private String poplocation;
	
	private Long roleId;
	private String roleName;
	
	private List<Long> roleslist;
	
	private List<Long> citylist;
	
}
