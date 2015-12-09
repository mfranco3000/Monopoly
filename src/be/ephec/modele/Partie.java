package be.ephec.modele;

import java.util.ArrayList;

public class Partie {
	private ArrayList<Joueur> tabJoueurs = new ArrayList<Joueur>(2);
	private Plateau plateau = new Plateau(this);
	private int nbTour = 0;
	private int nbTourSuite = 0;
	private Joueur[] tabMonopoles = new Joueur[8];
	private int nbCarteChancePioche = 0;
	private int nbCarteCaisseComPioche = 0;
	private boolean flagDesDouble = false;
	
	public Partie(){
		initJoueur();
	}
	
	public static void main(String[] args) {
		Partie truc = new Partie();
		System.out.println(truc.plateau.getCarte("Chance", 0).texte+" carte 1");
		System.out.println(truc.plateau.getCarte("Chance", 1).texte+" carte 2");
		System.out.println(truc.plateau.getCarte("Chance", 2).texte+" carte 3");
		System.out.println(truc.plateau.getCarte("Chance", 3).texte+" carte 4");
		System.out.println(truc.plateau.getCarte("Chance", 4).texte+" carte 5");
		System.out.println(truc.plateau.getCarte("Chance", 5).texte+" carte 6");
		System.out.println(truc.plateau.getCarte("Chance", 6).texte+" carte 7");
		System.out.println(truc.plateau.getCarte("Chance", 7).texte+" carte 8");
		System.out.println(truc.plateau.getCarte("Chance", 8).texte+" carte 9");
		System.out.println(truc.plateau.getCarte("Chance", 9).texte+" carte 10");
		System.out.println(truc.plateau.getCarte("Chance", 10).texte+" carte 11");
		System.out.println(truc.plateau.getCarte("Chance", 11).texte+" carte 12");
		System.out.println(truc.plateau.getCarte("Chance", 12).texte+" carte 13");
		System.out.println(truc.plateau.getCarte("Chance", 13).texte+" carte 14");
		System.out.println(truc.plateau.getCarte("Chance", 14).texte+" carte 15");
		System.out.println(truc.plateau.getCarte("Chance", 15).texte+" carte 16");
		System.out.println(truc.plateau.getCarte("Chance", 0).texte+" carte 17");

		
	}
	
	public void debutTour(){
		while(plateau.getSommeDes() == 0){
			/*le programme attend que le joueur lance les d�s.
			 * Pendant ce temps, le joueur peut acheter des maisons/hotels, demander des loyers et vendre des biens.*/
			// La m�thode LancerDes() est li�e au bouton dans la GUI.
			}
		/*Le vient de lancer les d�s*/
		if(plateau.getDe1().getValeur()==plateau.getDe2().getValeur())
			flagDesDouble=true;
		if(getJoueurCourant().getNbTourPrison()>0){
			getJoueurCourant().setNbTourPrison(getJoueurCourant().getNbTourPrison()+1);
			if(getJoueurCourant().getNbTourPrison()>3){
				if(plateau.getDe1().getValeur()!=plateau.getDe2().getValeur())
					retraitSolde(50, getJoueurCourant());
				avancer(plateau.getSommeDes());
			}
			if(plateau.getDe1().getValeur() == plateau.getDe2().getValeur())
				avancer(plateau.getSommeDes());
		}
		else{
			if((nbTourSuite==2) && (flagDesDouble==true))
					getJoueurCourant().entreEnPrison();
			avancer(plateau.getSommeDes());
		}
		//while(/*quelque chose*/){
			/*le programme attend que le joueur clique sur le bouton "Fin de tour".
			 * Pendant ce temps, le joueur peut acheter des maisons/hotels, demander des loyers et vendre des biens.*/
		finTour();
	}
	
	public void finTour(){	//Note: Bouton "fin de tour" uniquement cliquable apr�s avoir lanc� les d�s.
		if( (!flagDesDouble) || (getJoueurCourant().getNbTourPrison()>0))
			nbTour++; //nbTour++ que si le joueur n'a pas fait un double OU si le joueur est en prison en fin de tour.
		else //Sinon, �a veut dire que le joueur a fait un double et n'a pas fini son tour en prison.
			nbTourSuite++;
		plateau.getDe1().setZero();
		plateau.getDe2().setZero();
		flagDesDouble=false;
		debutTour();
	}
	
	private void initJoueur() {
		tabJoueurs.add(new Joueur(this, "Joueur 1"));
		tabJoueurs.add(new Joueur(this, "Joueur 2"));
	}

	public void acheter(Case terrain){ //L'argument est la case sur laquelle le joueur qui appelle la m�thode se trouve.
		if((terrain.getType() == "Propri�t�" || terrain.getType() == "gare"  || terrain.getType() == "service") && terrain.getProprietaire() == null){
			if(getJoueurCourant().getSolde() - terrain.getPrixTerrain() >= 0){
				retraitSolde(terrain.getPrixTerrain(),getJoueurCourant());
				getJoueurCourant().getTabPossessions().add(terrain);
			}
		}
		else { // si c'est pas le bon type ou d�j� achet� => envoie msg au joueur 
			System.out.println("achat impossible !");
		}
	}
	
	public void vendre(Case x, int valeur, Joueur acheteur){ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! � check pour la d�cision.
			// verif solde autre joueur + decision 
			if(/*decision &&*/ acheteur.getSolde() >= valeur ){
				ajoutSolde(valeur,getJoueurCourant());
				getJoueurCourant().getTabPossessions().remove(x);
			}
	}
	
	public void avancer(int x){ //Sert � avancer de X cases.
		int anciennePosition = getJoueurCourant().getPosition();
		getJoueurCourant().setPosition((getJoueurCourant().getPosition() + x)%40);
		checkPasseCaseDepart(anciennePosition);
		plateau.getTabCases()[getJoueurCourant().getPosition()].action();
	}
	
	public void allerA(int x){ //Sert � placer le joueurCourant � l'indice X du plateau.
		int anciennePosition = getJoueurCourant().getPosition();
		getJoueurCourant().setPosition(x);
		checkPasseCaseDepart(anciennePosition);
		plateau.getTabCases()[getJoueurCourant().getPosition()].action();
	}
	
	public void checkPasseCaseDepart(int anciennePosition){
		if((getJoueurCourant().getPosition() < anciennePosition) && getJoueurCourant().getNbTourPrison() == 0)
			ajoutSolde(200,getJoueurCourant());
	}
	

	
	public void retraitSolde(int x, Joueur player){
		player.setSolde(-x);
		if (player.getSolde()<0)
			Perdu(player);
	}
	
	public void Perdu(Joueur player) {
		// envoyer un msg au joueur 
		//supprimer le joueur perdant
		tabJoueurs.remove(player);
		if(tabJoueurs.size()<2)
			Gagne(tabJoueurs.get(0));		
	}
	
	public void Gagne(Joueur player) {
		//envoyer un msg au joueur 
		// terminer la partie
	}
	
	public void ajoutSolde(int x, Joueur player){
		player.setSolde(x);
	}

	public ArrayList<Joueur> getTabJoueurs() {
		return tabJoueurs;
	}
	
	public Joueur getJoueurCourant(){
		return tabJoueurs.get(nbTour % tabJoueurs.size());
	}
	
	public Plateau getPlateau(){
		return plateau;
	}
	
	public int getNbCarteChancePioche(){
		return nbCarteChancePioche;
	}
	public void setNbCarteChancePioche(int nb){
		nbCarteChancePioche=nb;
	}
	
	public int getNbCarteCaisseComPioche(){
		return nbCarteCaisseComPioche;
	}
	
	public void setNbCarteCaisseComPioche(int nb){
		nbCarteCaisseComPioche=nb;
	}
	
	public void setNbTourSuite(int nb) {
		nbTourSuite=nb;
	}
}