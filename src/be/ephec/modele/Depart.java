/**
 * @author Marchal François et Massart Florian
 * @version 1.0
 */

package be.ephec.modele;

public class Depart extends Case {
	
	/**
	 * Constructeeur de case départ
	 * @param partie : la partie en cours
	 */
	public Depart(Partie partie){
		super(partie,"Depart","Case Depart");
	}
	
	/**
	 * Methode pour l'action de la case départ
	 */
	public void action(){
		//Rien à mettre dans cette méthode.
	}

	@Override
	public String getProprietaire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProprietaire(String nom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrixTerrain() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbMaison() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbHotel() {
		// TODO Auto-generated method stub
		return 0;
	}
}


