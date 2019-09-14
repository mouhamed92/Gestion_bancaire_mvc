package org.sid.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecController {

	
	@RequestMapping("/login")
	public String login(){
		return "login";
		
	}
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/operation";
		
	}
	
	@RequestMapping("/403")
	public String accesDenid(){
		return "403";
		
	}
}
