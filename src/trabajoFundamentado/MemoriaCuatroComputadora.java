package trabajoFundamentado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MemoriaCuatroComputadora extends JFrame implements ActionListener{
	JButton arreglo [] = new JButton[16];
	Carta cartas [] = new Carta [16];
	Carta temporal = new Carta (0, "Fondo.png",0);
	int par = 0;
	static int punt1 = 0;
	static int punt2 = 0;
	int posicion = 0;
	static String nom1 = pedirNombre1();
	static String nom2 = jugadorComputadora();
	public MemoriaCuatroComputadora() {
		inciarTablero();
	}
	public final void inciarTablero() {
		this.setSize(600, 600);
		this.setTitle("Memoria");
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int contador = 0;
		for(int ii = 0; ii <4; ii++) {
			for (int jj = 0; jj< 4; jj++) {
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
		for(int i=1; i<=8; i++) {
			Carta carta1 = new Carta(i,i+".png", c);
			Carta carta2 = new Carta(i, i+".png", c+1);
			cartas[c] = carta1;
			c++;
			cartas[c] = carta2;
			c++;
		}
		int llenar = 0;
		Carta cartaTemporal [] = new Carta[16];
		for(int k = 0; k< cartaTemporal.length; k++) {
			cartaTemporal[k] =new Carta(0, "Hide.png", -1);
		}
		while(llenar<=15){
			int aleatorio =((int)(Math.random()*16));
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
	public void Jugar() {
		for (int bb = 0; bb < arreglo.length; bb++) {
			bb = (int) (Math.random() * 15);
			if (cartas[bb].revelado == false) {
				cartas[bb].btn.setIcon(cartas[bb].img);
				if (par == 0) {
					cartas[bb].revelado = true;
					temporal = cartas[bb]; 
					par = 1;
				} else {
					par = 0;
					if (cartas[bb].valor == temporal.valor) {
						System.out.println(bb);
						cartas[bb].revelado = true;
						punt2++;
						if (punt1 + punt2 == 8) {
							ElegirGanador(punt1, punt2);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Turno de jugador:" + nom1);
						try {
							
							cartas[bb].btn.update(cartas[bb].btn.getGraphics());
							Thread.sleep(500);
							tapar(bb);
						} catch (Exception e2) {
							System.out.println(e2);
						}
						actionPerformed(null);
					}
				}
			}
		}
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
						System.out.println(i);
						cartas[i].revelado =true;
						punt1++;
						if (punt1 + punt2 == 8) {
							ElegirGanador(punt1, punt2);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Turno de jugador:" + nom2);
						try {
							cartas[i].btn.update(cartas[i].btn.getGraphics());
							Thread.sleep(500);
							tapar(i);
						} catch (Exception e2) {
							System.out.println(e2);
						}
						Jugar(); 
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
	public void ElegirGanador(int punt1, int punt2) {
		if (punt1 == punt2) {
			JOptionPane.showMessageDialog(this, "quedaron en empate!");
		} else {
			if (punt1 > punt2) {
				JOptionPane.showMessageDialog(this,
						"Felicidades" + nom1 + "Ganaste, El puntaje es: " + punt1);
			} else {
				JOptionPane.showMessageDialog(this, "Felicidades" + nom2 + "Ganaste, El puntaje es: " + punt2);
			}
		}
		
	}
	public static String pedirNombre1() {
		String nombre1 = JOptionPane.showInputDialog("Nombre de usuario ");
		System.out.println(nombre1);
		return nombre1;
	}
	public static String jugadorComputadora() {
		String nombre2 = "Computadora";
		return nombre2;
	}

}
