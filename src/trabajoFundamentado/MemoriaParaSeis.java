package trabajoFundamentado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MemoriaParaSeis extends JFrame implements ActionListener{
	JButton arreglo [] = new JButton[36];
	Carta cartas [] = new Carta [36];
	Carta temporal = new Carta (0, "Fondo.png",0);
	int par = 0;
	public MemoriaParaSeis() {
		inciarTableroParaSeis();
	}
	public final void inciarTableroParaSeis() {
		this.setSize(1000, 1000);
		this.setTitle("Memoria");
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int contador = 0;
		for(int ii = 0; ii <6; ii++) {
			for (int jj = 0; jj< 6; jj++) {
				JButton btn = new JButton("", new ImageIcon(this.getClass().getResource("Hide.png")));
				btn.setBounds((ii+1)*65,(jj+1)*88,65, 88);
				btn.setName(contador+"");
				btn.addActionListener(this);
				arreglo[contador]=btn;
				contador++;
				this.add(btn);
			}
		}
		revolver();
	}
	
	public void revolver(){
		int c=0;
		for(int i=1; i<=18; i++) {
			Carta carta1 = new Carta(i,i+".png", c);
			Carta carta2 = new Carta(i, i+".png", c+1);
			cartas[c] = carta1;
			c++;
			cartas[c] = carta2;
			c++;
		}
		int llenar = 0;
		Carta cartaTemporal [] = new Carta[36];
		for(int k = 0; k< cartaTemporal.length; k++) {
			cartaTemporal[k] =new Carta(0, "Hide.png", -1);
		}
		while(llenar<=35){
			int aleatorio =((int)(Math.random()*36));
			if(buscarNum(aleatorio,cartaTemporal)) {
				cartaTemporal [llenar] = cartas[aleatorio];
				cartaTemporal [llenar].btn =arreglo[llenar];
				llenar++;
			}
		}
		cartas=cartaTemporal;		
	}
	public boolean buscarNum(int num, Carta c[]) {
		int con = 0;
		for(Carta c1 : c) {
			if(num == c1.posicion) {
				con++;
			}
		}
		return(con<1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i< arreglo.length; i++) {
			if(cartas[i].btn ==e.getSource()&& cartas[i].revelado == false) {
				cartas[i].btn.setIcon(cartas[i].img);
				if(par==0) {
					cartas[i].revelado=true;
					temporal=cartas[i];
					par=1;	
				}else {
					par=0;
					if(cartas[i].valor == temporal.valor) {
						cartas[i].revelado =true;
						boolean bandera=true;
						for(Carta elemento : cartas) {
							if(elemento.revelado== false) {
								bandera=false;
								break;
							}
						}
						if (bandera) {
							JOptionPane.showMessageDialog(this, "felicidades Ganaste");
						}
					}else {
						try{
							cartas[i].btn.update(cartas[i].btn.getGraphics());
							Thread.sleep(500);
							tapar(i);
						}catch (Exception ex) {
							System.out.println(ex);
						}
					}
				}
			}
		}
	}
	public void tapar(int a) {
		cartas[a].btn.setIcon(new ImageIcon(this.getClass().getResource("Hide.png")));
		cartas[Integer.valueOf(temporal.btn.getName())].revelado=false;
		cartas[Integer.valueOf(temporal.btn.getName())].btn.setIcon(new ImageIcon(this.getClass().getResource("Hide.png")));
	}
	public static void main (String[] args) {
	new Memoria().setVisible(true);
	}
}
