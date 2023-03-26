package co.u.ser.otmUni;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u.dto.DtoUsers;

import co.u.model.Citymst_cm1;
import co.u.repo.otmUni.RepoCitymst_cm1;

@Service
public class SerImplCitymst_cm1 implements SerCitymst_cm1{

	
	@Autowired
	private RepoCitymst_cm1 repoCity;
	
	@Override
	public List<DtoUsers> returnListOfCity() {
		List<DtoUsers> dto=repoCity.findAll().stream().map(this::convertEntityToDto1)
				.collect(Collectors.toList());
		
		return dto;
	}

	
	public DtoUsers convertEntityToDto1(Citymst_cm1 cm1) {
		DtoUsers dto=new DtoUsers();
		dto.setCityName(cm1.getCityName());
		dto.setCityId(cm1.getId());
		return dto;
	}
}
