package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IbanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BanueController {

	@Autowired
	private IbanqueMetier ibanque ;
	
	@RequestMapping("/operation")
	public String index(){
		return "index";
		
	}
	
	@RequestMapping("/consulterCpte")
	public String consulterCpte(Model model , String codeCpte){
		
			model.addAttribute("codeCpte",codeCpte);
		try {
			Compte compte = ibanque.consulterCompte(codeCpte);
			model.addAttribute("compte",compte);
			Page<Operation> operations = ibanque.listeOperation(codeCpte, 0, 4);
			model.addAttribute("operation", operations.getContent());
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		
		return "operation";
	}		
	
}
