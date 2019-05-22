import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;

public class Graphic extends JFrame {
	GA a;
	private static final long serialVersionUID = 1L;
	public Graphic() {
		setTitle("Caixeiro Viajante");
		setSize(900, 900);
		setVisible(true);
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void repaint(Graphics g, Cidade [] c, GA b) {
		if(a == null) a = new GA (b);
		if(a.getFitness() != b.getFitness()) {repaint(); a = new GA (b);}
		for(int i=0;i<c.length;i++) {
			if(i==0) g.setColor(Color.RED);
			g.fillOval((int) c[i].getP().getX()+18,(int) c[i].getP().getY()+38, 14, 14);
			g.setColor(Color.BLACK);
			if(i < c.length-1)
				g.drawLine((int) b.getCaminho()[i].getP().getX()+25, (int) b.getCaminho()[i].getP().getY()+45, (int) b.getCaminho()[i+1].getP().getX()+25, (int) b.getCaminho()[i+1].getP().getY()+45);
			else
				g.drawLine((int) b.getCaminho()[i].getP().getX()+25, (int) b.getCaminho()[i].getP().getY()+45, (int) b.getCaminho()[0].getP().getX()+25, (int) b.getCaminho()[0].getP().getY()+45);
		}
	}
}
