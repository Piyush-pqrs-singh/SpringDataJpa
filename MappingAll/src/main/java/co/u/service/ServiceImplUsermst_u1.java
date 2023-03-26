package co.u.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u.dto.DtoUsers;

import co.u.model.Addressmst_add1;
import co.u.model.Citymst_cm1;
import co.u.model.Rolemst_rm1;
import co.u.model.Statemst_sm1;
import co.u.model.Usermst_u1;
import co.u.repo.otmUni.RepoCitymst_cm1;
import co.u.repo.otmUni.RepoRolemst_rm1;
import co.u.repo.otmUni.RepoStatemst_sm1;
import co.u.repo.otmUni.RepoUsermst_u1;

@Service
public class ServiceImplUsermst_u1 implements ServiceUsermst_u1{

	@Autowired
	private RepoUsermst_u1 repoUsermst_u1;
	
	@Autowired
	private RepoRolemst_rm1 repoRole_mst;
	
	@Autowired
	private RepoStatemst_sm1 repoStatemst;
	
	@Autowired
	private RepoCitymst_cm1 repoCity;
	
	@Override
	public void saveUser(DtoUsers dto) {
		System.out.println(dto+ " PLEASE CHECK HERE");
		
		Usermst_u1 u1=new Usermst_u1();
		u1.setFirstName(dto.getFirstName());
		u1.setLastName(dto.getLastName());
		u1.setContactNo(dto.getContactNo());
		u1.setEmail(dto.getEmail());
		u1.setPassword(dto.getPassword());
		
		Addressmst_add1 ad1=new Addressmst_add1();
		ad1.setCity(dto.getCity());
		ad1.setState(dto.getState());
		ad1.setCountry(dto.getCountry());
		ad1.setZipCode(dto.getZipCode());
		u1.setAddress(ad1);
		
		
		Rolemst_rm1 role=null;
		try {
		
		for(int i=0;i<dto.getRoleslist().size();i++) {
			
			role=repoRole_mst.findById(dto.getRoleslist().get(i)).get();
			
			role.setRoleName(role.getRoleName());
			role.setId(role.getId());
	        repoRole_mst.save(role);
			u1.getRole().addAll(List.of(role));
			
			
		}
		
		
		}catch(Exception ex) { ex.printStackTrace();  }
		
		
		Statemst_sm1 sm1=repoStatemst.findById(dto.getStateId()).get();
		sm1.setId(dto.getStateId());
		repoStatemst.save(sm1);
	
		u1.setStatemst_sm1(sm1);
         
		Citymst_cm1 cm1=null;
		try {
			
			for(int i=0;i<dto.getCitylist().size();i++) {
				 
				cm1=repoCity.findById(dto.getCitylist().get(i)).get();
				cm1.setCityName(cm1.getCityName());
				cm1.setId(cm1.getId());
				//cm1.setState(sm1);
				repoCity.save(cm1);
				
				u1.getCityList().addAll(List.of(cm1));
			}
//			cm1=repoCity.findById(null)
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		repoUsermst_u1.save(u1);
		
	}

}
