package be.ephec.modele;

import java.io.Serializable;

public class De implements Serializable{
	private int valeur;
	
	public De(){
	}
	
	public int lancerDe(){
		valeur = (int)(Math.random()*6) + 1;
		return valeur;
	}
	
	public int getValeur(){
		return valeur;
	}
	
	public void setZero(){
		valeur=0;
	}
	
}