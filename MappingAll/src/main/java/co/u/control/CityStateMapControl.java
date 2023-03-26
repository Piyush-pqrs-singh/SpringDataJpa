package co.u.control;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.u.dto.DtoCityState;
import com.u.dto.DtoUsers;

import co.u.changer.UserChanger;
import co.u.model.Citymst_cm1;
import co.u.model.Poplocationmst_pop1;
import co.u.model.Rolemst_rm1;
import co.u.model.Statemst_sm1;

import co.u.repo.otmUni.RepoPoplocationmst_pop1;
import co.u.repo.otmUni.RepoRolemst_rm1;
import co.u.repo.otmUni.RepoStatemst_sm1;
import co.u.ser.otmUni.SerCitymst_cm1;
import co.u.ser.otmUni.SerStatemst_sm1;

@Controller
public class CityStateMapControl {

	public static String m_strStateName="";
	public static Long m_strStateId=0L;
	
    @Autowired
    private SerStatemst_sm1 serStatemst_sm1;
    
    @Autowired
    private RepoStatemst_sm1 repoStatemst_sm1;
    
    
    @Autowired
    private SerCitymst_cm1 serCity;
    
	 @Autowired
	 private RepoRolemst_rm1 repoRole;
	
	
    
//    @Autowired 
//    private RepoCitymst_cm1 repoCity;
    
    @Autowired
    private RepoPoplocationmst_pop1 repoLocation;
    
    
	@GetMapping("/addcity")
	public String addCity(Model model) {
		
		List<DtoCityState> l1=serStatemst_sm1.returnListOfState();
		model.addAttribute("l1", l1);
		
		DtoCityState dtoCityState=new DtoCityState();
		model.addAttribute("dto", dtoCityState);
		
		return "CityState/addcity";
	}
	
	
	
	@PostMapping("/savecity")
	public String saveCity(DtoCityState dto,Model model) {
		System.out.println(dto.getCityName()+ " CityName");
		System.out.println(dto.getStateId()+ " StateId");
		
		Statemst_sm1 sm1=repoStatemst_sm1.findById(dto.getStateId()).get();
		
		Citymst_cm1 c1=new Citymst_cm1();
		c1.setCityName(dto.getCityName());
		
		//Only For Bidirectional//
		 c1.setState(sm1);
		//Only For Bidirectional//
		
		sm1.getCity().add(c1);
		
		repoStatemst_sm1.save(sm1);		
		return "redirect:/addcity";
	}
	
	@GetMapping("/viewcity")
	public String viewCity(Model model) {
		Statemst_sm1 statemst_sm1=repoStatemst_sm1.findById(1L).get();
	   	
		m_strStateId=statemst_sm1.getId();
		m_strStateName=statemst_sm1.getStateName();
		
		List<DtoCityState> l1=statemst_sm1.getCity().stream().
               map(this::convertEntityToDto)
               .collect(Collectors.toList());
		
		model.addAttribute("l1", l1);
		return "CityState/viewcity";
	}
	
	public DtoCityState convertEntityToDto(Citymst_cm1 order2) {
		DtoCityState dto = new DtoCityState();
        dto.setCityName(order2.getCityName());
        dto.setStateName(m_strStateName);
        dto.setStateId(m_strStateId);
        return dto;
    }
	
	
	
	/**** POPLOCATION ******\ 
	/***********************/
	@GetMapping("/addpoplocation")
	public String addPoplocation(Model model) {
		DtoUsers dto=new DtoUsers();
		model.addAttribute("dto", dto);
		
		List<DtoUsers> l1=serCity.returnListOfCity();
		model.addAttribute("l1", l1);
		
		return "CityState/addPoplocation";
	}
	
	
	@PostMapping("/savepop")
	public String savePop(DtoUsers dto) {
		Poplocationmst_pop1 pop1=new Poplocationmst_pop1();
		pop1.setPoplocation(dto.getPoplocation());
		
		Citymst_cm1 cm1=new Citymst_cm1();
		cm1.setId(dto.getCityId());
		
		pop1.setCm1(cm1);
		repoLocation.save(pop1);
		return "redirect:/addpoplocation";
	}
	
	
	
	
	
	


	
	
	@GetMapping("/addselect")
	public String addUser(Model model,DtoUsers dto1) {
		DtoUsers users=new DtoUsers();
		model.addAttribute("dto", users);
		
		List<DtoCityState> l1=serStatemst_sm1.returnListOfState();
		model.addAttribute("l1", l1);
		
		
		List<DtoUsers> roleList=repoRole.findAll()
				.stream().map(this::convertEntityToDto2)
				.collect(Collectors.toList());	
		model.addAttribute("role", roleList);
		
		
		UserChanger u1=null;
		
		
			if(dto1.getStateId()!=null) {
				u1=new UserChanger("withState");
				Long x1=dto1.getStateId();
				Statemst_sm1 statemst_sm1=repoStatemst_sm1.findById(x1).get();
				
				m_strStateId=statemst_sm1.getId();
			
				model.addAttribute("m_strStateId",m_strStateId);
				
				m_strStateName=statemst_sm1.getStateName();
				model.addAttribute("m_strStateName",m_strStateName);
				
				List<DtoUsers> l2=statemst_sm1.getCity().stream().
		               map(this::convertEntityToDto2)
		               .collect(Collectors.toList());
				
				model.addAttribute("l2", l2);
				model.addAttribute("u1", u1);
				
			}else {
				
				u1=new UserChanger("withoutState");
				model.addAttribute("u1", u1);
			}
		
		//return "showForm";
		
	return "CityState/selectList";
	}
	
	
	
	@PostMapping("/saverole1")
	public String saveRoles(DtoUsers dto) {
		System.out.println("RADMAN "+dto.getRoleslist());
		
		return "redirect:/addselect";
	}
	
	public DtoUsers convertEntityToDto2(Rolemst_rm1 cm1) {
		DtoUsers dto=new DtoUsers();
		dto.setRoleName(cm1.getRoleName());
		dto.setRoleId(cm1.getId());
		return dto;
	}
	
	public DtoUsers convertEntityToDto2(Citymst_cm1 order2) {
		DtoUsers dto = new DtoUsers();
        dto.setCityName(order2.getCityName());
        dto.setCityId(order2.getId());
        dto.setStateName(m_strStateName);
        dto.setStateId(m_strStateId);
        return dto;
    }
	
}
