package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IbanqueMetier {

	public Compte consulterCompte(String CodeCpte);
	public void verser(String CodeCpte,double montant);
	public void retirer(String CodeCpte,double montant);
	public void virement(String CodeCpte1 , String CodeCpte2,double montant);
	public Page<Operation> listeOperation(String CodeCpte , int page , int size );
	
}
