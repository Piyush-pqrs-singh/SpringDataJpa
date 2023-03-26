package co.u.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.u.dto.DtoUsers;

import co.u.model.CustomerNamemst_cm1;
import co.u.model.PrincipleCustmst_pc1;
import co.u.repo.otmUni.RepoCustomerNamemst_cm1;
import co.u.ser.otmUni.SerPrincipleCustmst_pc1;

@Controller
public class ControllerMapping {
	
	@Autowired
	private SerPrincipleCustmst_pc1 serPrincipleCustmst_pc1;
	
	@Autowired 
	private RepoCustomerNamemst_cm1 repoCustomerNamemst_cm1;
	
	@GetMapping("/addcust")
	public String addCust(Model model) {
		System.out.println("PIYUSH IS KING");
		DtoUsers dto=new DtoUsers();
		model.addAttribute("dto", dto);
		
		
		List<DtoUsers> l1=serPrincipleCustmst_pc1.returnListOfPc();
		model.addAttribute("l1", l1);

		 
		System.out.println("PIYUSH IS KING");
		return "web/addPcCust";
		
	}
	
	
	@PostMapping("/savecustomer")
	public String saveCustomer(DtoUsers dto) {
		System.out.println(dto.getPcId()+" PC ID");
		CustomerNamemst_cm1 cm1=new CustomerNamemst_cm1();
		cm1.setCustName(dto.getCustName());
		cm1.setContactNo(dto.getCustContact());
		cm1.setCustAddress(dto.getCustAddress());
		
		  PrincipleCustmst_pc1 pc1=new PrincipleCustmst_pc1();
		  pc1.setId(dto.getPcId());
	   cm1.setPc(pc1);
		
	repoCustomerNamemst_cm1.save(cm1);
		return "redirect:/addcust";
	}
	
	
	@GetMapping("/recordticket")
	public String recordTicket() {
		
		return "web/recordticket";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
////	@GetMapping("/showform")
////	public String showForm(Model model) { 
////		DtoUsers d1=new DtoUsers();
////		model.addAttribute("user", d1);
////		//System.out.println("Piyush");
////		return "showForm";	
////		
////	}
////	
////	
////	@GetMapping("/showStateCity")
////	public 
//}	

}