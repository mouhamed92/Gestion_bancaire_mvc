package org.sid.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private double decouverte ;

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client) {
		super(codeCompte, dateCreation, solde, client);
		// TODO Auto-generated constructor stub
	}

	public double getDecouverte() {
		return decouverte;
	}

	public void setDecouverte(double decouverte) {
		this.decouverte = decouverte;
	}
	
	
}
