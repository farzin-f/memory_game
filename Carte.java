import javax.swing.JComponent;
import java.awt.*;
import java.util.Random;

public abstract class Carte extends JComponent {
    
    private boolean recto;

    /**
       Constructeur
       @param recto détermine si le recto de la carte est montré
       */
    protected Carte (boolean recto) {
	this.recto = recto;
    }

    /**
       Constructeur - créée une copie d'une carte existante
       @param c carte à copier
     */
    protected Carte (Carte c) {
	this.recto = c.recto;
    }

    /**
       Vérifie si le recto de la carte est montré
       @return boolean - true si le recto est montré
     */
    boolean estMontree() {
	return recto;
    }

    /**
       Vérifie si le verso de la carte est montré
       @return boolean - true si le verso est montré
     */
    boolean estCachee() {
	return !recto;
    }

    /**
       Redessine une carte pour montrer son recto
     */
    public void montre() {
	recto = true;
	repaint();
    }

    /**
       Redessine une carte pour montrer son verso
     */
    public void cache() {
	recto = false;
	repaint();
    }

    /**
       Redessine une carte pour montrer le côté opposé
     */
    public void retourne() {
	recto = !recto;
	repaint();
    }

    /**
       Dessine le verso de la carte
       @param g contexte graphique de la carte
     */
    public void paintVerso (Graphics2D g) {
	g.setColor(Color.black);
	g.fillRect(0, 0, getWidth(), getHeight());
      }

    /**
       Dessine le recto de la carte
       @param g contexte graphique de la carte
     */
    public abstract void paintRecto (Graphics2D g);

    /**
       Redéfinition de paintComponent
       @param g contexte graphique de la carte
     */
    public void paintComponent (Graphics g) {

	Graphics2D g2 = (Graphics2D) g;
	
	g2.setColor(Color.black);
	super.paintComponent(g2);

	if (recto) {
	    paintRecto(g2);
	} else {
	    paintVerso(g2);
	}
    }

    /**
       Vérifie si deux cartes sont identiques
       @param c carte à comparer
       @return boolean - true si les cartes sont identiques
     */
    public abstract boolean rectoIdentique (Carte c);

    /**
       Créée une copie d'un objet
       @return objet identique
     */
    public abstract Object duplique();

    /**
       Mélange les cartes d'un tableau de cartes
       @param cartes tableau de cartes à mélanger
     */
    public static void melangeCartes(Carte[] cartes) {
	
	Carte tmp;
	Random rand = new Random();
	for (int i = 0; i < cartes.length; i++) {
	    int index = rand.nextInt(cartes.length);
	    tmp = cartes[i];
	    cartes[i] = cartes[index];
	    cartes[index] = tmp;
	    
	}
    }
}
