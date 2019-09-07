package org.sid;

import java.util.Date;

import org.sid.dao.ClienRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.IbanqueImp;
import org.sid.metier.IbanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestionBancaireApplication implements CommandLineRunner {

	@Autowired
	private ClienRepository clienRepository ;
	@Autowired
	private CompteRepository compteRepository ;
	@Autowired
	private OperationRepository operationRepository ;
	@Autowired
	private IbanqueMetier ibanqueMetier ;
	
	public static void main(String[] args)  {
		SpringApplication.run(GestionBancaireApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Client c1 = clienRepository.save(new Client("mohamed", "mohamed@hotmail.com"));
		Client c2 = clienRepository.save(new Client("aala", "aala@gmail.com") );
		
		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 9000, c1)) ;
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 5000, c2,5.5)) ;
		
		operationRepository.save(new Versement(new Date(), 100, cp1));
		operationRepository.save(new Versement(new Date(), 500, cp1));
		operationRepository.save(new Retrait(new Date(), 300, cp1));
		
		operationRepository.save(new Versement(new Date(), 400, cp2));
		operationRepository.save(new Versement(new Date(), 200, cp2));
		operationRepository.save(new Retrait(new Date(), 700, cp2));
		 
		ibanqueMetier.verser("c1", 10000);
	 
	}

}

