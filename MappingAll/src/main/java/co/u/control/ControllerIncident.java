package co.u.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerIncident {

	@GetMapping("/addincident")
	public String addIncident() {
		return "addincident";
	}
	
}
