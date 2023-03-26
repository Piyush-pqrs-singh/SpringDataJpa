package co.u.control;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.u.dto.DtoCityState;
import com.u.dto.DtoUsers;

import co.u.changer.UserChanger;
import co.u.model.Citymst_cm1;
import co.u.model.Rolemst_rm1;
import co.u.model.Statemst_sm1;
import co.u.repo.otmUni.RepoRolemst_rm1;
import co.u.repo.otmUni.RepoStatemst_sm1;
import co.u.ser.otmUni.SerStatemst_sm1;
import co.u.service.ServiceUsermst_u1;

@Controller
public class ControllerUser {

	public static String m_strStateName="";
	public static Long m_strStateId=0L;
	
	
	 @Autowired
	 private SerStatemst_sm1 serStatemst_sm1;
	
	 @Autowired
	 private RepoRolemst_rm1 repoRole;
	 
	   @Autowired
	    private RepoStatemst_sm1 repoStatemst_sm1;
	   
	   @Autowired
	   private ServiceUsermst_u1 serviceUsermst_u1;
	 
	@GetMapping("/adduser")
	public String addUser(Model model,DtoUsers dto1) {
		DtoUsers users=new DtoUsers();
		model.addAttribute("dto", users);
		
		List<DtoCityState> l1=serStatemst_sm1.returnListOfState();
		model.addAttribute("l1", l1);
		
		
		List<DtoUsers> roleList=repoRole.findAll()
				.stream().map(this::convertEntityToDto1)
				.collect(Collectors.toList());	
		model.addAttribute("role", roleList);
		
		
		UserChanger u1=null;
		
		
			if(dto1.getStateId()!=null) {
				u1=new UserChanger("withState");
				model.addAttribute("u1", u1);				
				
				Long x1=dto1.getStateId();
				Statemst_sm1 statemst_sm1=repoStatemst_sm1.findById(x1).get();
				
				m_strStateId=statemst_sm1.getId();		
				model.addAttribute("m_strStateId",m_strStateId);
				
				m_strStateName=statemst_sm1.getStateName();
				model.addAttribute("m_strStateName",m_strStateName);
				
				List<DtoUsers> l2=statemst_sm1.getCity().stream().
		               map(this::convertEntityToDto)
		               .collect(Collectors.toList());
				model.addAttribute("l2", l2);
				
				
			}else {
				
				u1=new UserChanger("withoutState");
				model.addAttribute("u1", u1);
			}
		
		return "showForm";
		
	//return "CityState/selectList";
	}
	
	
	
	
	@PostMapping("/saveuser")
	public String saveUser(DtoUsers dto,@RequestParam("stateIdMen") String id) {
	
		Long varLong = Long.parseLong(id);
		dto.setStateId(varLong);
		serviceUsermst_u1.saveUser(dto);
		
		
		return "redirect:/adduser";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/saverole")
	public String saveRoles(DtoUsers dto) {
		System.out.println("RADMAN "+dto.getRoleslist());
		
		return "redirect:/adduser";
	}
	
	public DtoUsers convertEntityToDto1(Rolemst_rm1 cm1) {
		DtoUsers dto=new DtoUsers();
		dto.setRoleName(cm1.getRoleName());
		dto.setRoleId(cm1.getId());
		return dto;
	}
	
	
	public DtoUsers convertEntityToDto(Citymst_cm1 order2) {
		DtoUsers dto = new DtoUsers();
        dto.setCityName(order2.getCityName());
        dto.setCityId(order2.getId());
        dto.setStateName(m_strStateName);
        dto.setStateId(m_strStateId);
        return dto;
    }
}
