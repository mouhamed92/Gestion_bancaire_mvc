package org.sid.web;



import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IbanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		return "index";
	}	
	
	@RequestMapping(value="/operation",method=RequestMethod.POST)
	public String operation(Model model,String codeCpte, String typeOperation,
			double montant,String codeCpte2){
		
		try {
			if(typeOperation.equals("vers"))
				ibanque.verser(codeCpte, montant);
			
			if(typeOperation.equals("retr"))
				ibanque.retirer(codeCpte, montant);
			
			if(typeOperation.equals("vire"))
				ibanque.virement(codeCpte, codeCpte2, montant);
				
			
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCpte?codeCpte="+codeCpte+"&error="+e.getMessage();
		}
	 
		return "redirect:/consulterCpte?codeCpte="+codeCpte ;
		
	}
	
}
