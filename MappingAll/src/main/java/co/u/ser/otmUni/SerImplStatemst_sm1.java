package co.u.ser.otmUni;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u.dto.DtoCityState;

import co.u.model.Statemst_sm1;
import co.u.repo.otmUni.RepoStatemst_sm1;

@Service
public class SerImplStatemst_sm1 implements SerStatemst_sm1{

	@Autowired
	private RepoStatemst_sm1 repoStatemst_sm1;
	
	@Override
	public List<DtoCityState> returnListOfState() {
		List<DtoCityState> listState = repoStatemst_sm1.findAll().
                stream().
                map(this::convertEntityToDto).
                collect(Collectors.toList());
		return listState;
	}

	public DtoCityState convertEntityToDto(Statemst_sm1 s1) {
		DtoCityState dto=new DtoCityState();
		dto.setStateId(s1.getId());
		dto.setStateName(s1.getStateName());
		return dto;
    }
	
}
