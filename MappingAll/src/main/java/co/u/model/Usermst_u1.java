package co.u.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usermst_u1")
public class Usermst_u1 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String contactNo;
	
	private int enabled;
	
	@CreationTimestamp
    private LocalDateTime localDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Addressmst_add1 address;
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name="user_rolemst_ur1",
	           joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
	           inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
	          )
	private Set<Rolemst_rm1> role=new HashSet<>();
	
	@OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	private Statemst_sm1 statemst_sm1;
	

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinTable(name="users_city",
	  joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
	  inverseJoinColumns = @JoinColumn(name="city_id",referencedColumnName = "id")
	)
	private Collection<Citymst_cm1> cityList=new HashSet<>();

	
	
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="id_user",referencedColumnName = "id")
//	private List<IncidentMastermst_im1> incidentMaster=new ArrayList<>();
}
