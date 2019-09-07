package org.sid.metier;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IbanqueImp implements IbanqueMetier {

	@Autowired
	private CompteRepository CompteRepository ;
	@Autowired
	private OperationRepository operationRepository ;
	
	@Override
	public Compte consulterCompte(String CodeCpte) {
		
		Compte cp =CompteRepository.findOne(CodeCpte);
		
		if(cp==null)throw new RuntimeException("compte introuvable");
		return cp;
	}

	@Override
	public void verser(String CodeCpte, double montant) {
		Compte cp = consulterCompte(CodeCpte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		CompteRepository.save(cp);
	}

	@Override
	public void retirer(String CodeCpte, double montant) {
		Compte cp = consulterCompte(CodeCpte);
		double fassiliteCaisse = 0 ;
		
		if(cp instanceof CompteCourant)
			fassiliteCaisse=((CompteCourant) cp).getDecouverte();
		if(cp.getSolde()+fassiliteCaisse<montant)
			throw new RuntimeException("solde insuffisant");
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		CompteRepository.save(cp);
	}

	@Override
	public void virement(String CodeCpte1, String CodeCpte2, double montant) {
		retirer(CodeCpte1, montant);
		verser(CodeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listeOperation(String CodeCpte, int page, int size) {
		
		
		return operationRepository.listeOperation(CodeCpte, new PageRequest(page, size));
	}

}
