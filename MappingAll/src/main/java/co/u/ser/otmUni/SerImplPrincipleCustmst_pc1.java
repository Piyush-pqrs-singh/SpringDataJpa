package co.u.ser.otmUni;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u.dto.DtoUsers;

import co.u.model.PrincipleCustmst_pc1;
import co.u.repo.otmUni.RepoPrincipleCustmst_pc1;
@Service
public class SerImplPrincipleCustmst_pc1 implements SerPrincipleCustmst_pc1{

	
	@Autowired
	private RepoPrincipleCustmst_pc1 repoPrincipleCustmst_pc1;
	
	//**Return List Of PC TO SAVE CUSTOMER;
	@Override
	public List<DtoUsers> returnListOfPc() {
		 List<DtoUsers> l1= repoPrincipleCustmst_pc1.findAll()
				 .stream()
				 .map(this::convertEntityToDto1)
				 .collect(Collectors.toList());
		 
             return l1;
	}

	
	public DtoUsers convertEntityToDto1(PrincipleCustmst_pc1 pc1) {
		DtoUsers dto=new DtoUsers();
		dto.setPrincipleCustName(pc1.getPrincipleCustName());
		dto.setPcId(pc1.getId());
		return dto;
	}
}
