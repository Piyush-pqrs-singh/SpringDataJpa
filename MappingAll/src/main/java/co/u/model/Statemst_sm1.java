package co.u.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="statemst_sm1",
    uniqueConstraints = 
         @UniqueConstraint(name="stateName",columnNames = "state_name")
		)
public class Statemst_sm1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@javax.persistence.Column(name = "state_name",nullable = false)
	private String stateName;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "state")
	//@JoinColumn(name="id_state",referencedColumnName = "id")
	private List<Citymst_cm1> city=new ArrayList<Citymst_cm1>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "sm1")
	//@JoinColumn(name="id_state",referencedColumnName = "id")
	private List<CustomerNamemst_cm1> custName=new ArrayList<CustomerNamemst_cm1>();
	
	
}
