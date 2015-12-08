package be.ephec.modele;

public class Gare extends Case{
	private String proprietaire;
	private int prixGare;
	private int tabLoyers[];
	
	public Gare(Partie partie, String nom){
		super(partie,"Gare", nom);
		this.prixGare = 200;
		this.tabLoyers = new int[]{25,50,100,200};
	}
	
	public void action(){
		//Rien � mettre dans cette m�thode.
	}
	
	@Override
	public String getProprietaire() {
		return proprietaire;
	}
	
	@Override
	public int getPrixTerrain() {
		return prixGare;
	}
	
	@Override
	public int getNbMaison() {
		return 0;
	}
	
	@Override
	public int getNbHotel() {
		return 0;
	}
}