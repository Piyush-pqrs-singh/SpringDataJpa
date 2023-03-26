package co.u.model;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class CustomerNamemst_cm1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String custName;
	
	private String custAddress;
	
	private Integer contactNo;
	
	@ManyToOne
	@JoinColumn(name="pricust_id",referencedColumnName = "id")
	private PrincipleCustmst_pc1 pc;

	@ManyToOne
	@JoinColumn(name="state_id",referencedColumnName = "id")
	private Statemst_sm1 sm1;
	
     

	
	
}
