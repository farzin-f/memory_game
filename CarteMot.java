import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.*;

public class CarteMot extends Carte {

    private String mot;
    
    /**
       Constructeur
       @param recto détermine si le recto de la carte est montré
       @param mot mot à afficher sur le recto de la carte
    */
    protected CarteMot (boolean recto, String mot) {
	super(recto);
	this.mot = mot;
	
    }
    
    /**
       Constructeur - créée une copie d'une carte existante
       @param c carte à copier
    */
    protected CarteMot (CarteMot c) {
	super(c);
	this.mot = c.mot;
    }

    /**
       Dessine le recto de la carte (mot)
       @param g contexte graphique de la carte
    */
    public void paintRecto (Graphics2D g) {
	int hauteur = this.getHeight();
	int largeur = this.getWidth();
	Font f = new Font("Serif", Font.BOLD, 12);
	FontMetrics metrics = g.getFontMetrics(f);
	int motLength = metrics.stringWidth(this.mot);
	g.setFont(f);
	g.setColor(Color.black);
	g.drawString(this.mot, largeur/2-motLength/2, hauteur/2);
	g.drawRect(0, 0, largeur-1, hauteur-1);	
    }
    
    /**
       Vérifie si deux cartes sont identiques
       @param c carte à comparer
     */
    public boolean rectoIdentique (Carte c) {
	try {
	    return this.mot == ((CarteMot)c).mot;
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
    public Object duplique() {
	CarteMot c = new CarteMot(this);
	return c;
    }
    
    /**
       Redéfinition de toString
       @return String contenant l'état de la carte et le mot qui y apparaît
     */
    public String toString () {
        return ("montree:" + estMontree() + "mot: " + mot);
    }
    
}
