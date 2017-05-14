import javax.swing.JComponent;
import java.awt.*;

public class CarteCouleur extends Carte {

    private Color couleur;
    
    /**
       Constructeur
       @param recto détermine si le recto de la carte est montré
       @param couleur couleur du recto de la carte
       */
    protected CarteCouleur (boolean recto, Color couleur) {
	super(recto);
	this.couleur = couleur;
       	
    }

    /**
       Constructeur - créée une copie d'une carte existante
       @param c carte à copier
     */
    protected CarteCouleur (CarteCouleur c) {
	super(c);
	this.couleur = c.couleur;
    }

    /**
       Dessine le recto de la carte (carré de couleur)
       @param g contexte graphique de la carte
    */
    public void paintRecto (Graphics2D g) {
	g.setColor(this.couleur);
	g.fillRect(0, 0, getWidth(),getHeight());
    }

    /**
       Vérifie si deux cartes sont identiques
       @param c carte à comparer
     */
    public boolean rectoIdentique (Carte c) {
	try {
	return this.couleur == ((CarteCouleur)c).couleur;
	}
	//Si les cartes ne sont pas du même type, retourner false
	catch (ClassCastException e) {
	    return false;
	}
    }

    /**
       Créée une copie d'un objet en utilisant le constructeur de copie
       @return objet identique
    */
    public Object duplique () {
	CarteCouleur c = new CarteCouleur(this);
	return c;
    }

    /**
       Redéfinition de toString
       @return String contenant l'état de la carte et sa couleur
     */
    public String toString () {
	return ("montree: " + estMontree() + "couleur: " + couleur);
    }
}
