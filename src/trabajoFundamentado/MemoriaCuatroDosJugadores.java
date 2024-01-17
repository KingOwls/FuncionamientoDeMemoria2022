package trabajoFundamentado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MemoriaCuatroDosJugadores extends JFrame implements ActionListener{
	JButton arreglo [] = new JButton[16];
	Carta cartas [] = new Carta [16];
	Carta temporal = new Carta (0, "Fondo.png",0);
	int par = 0;
	static int punt1 = 0;
	int contPunt = 0;
	static int punt2 = 0;
	int posicion = 0;
	static String nom1 = pedirNombre1();
	static String nom2 = pedirNombreSegundo();
	int numJugador = ElegirJugador(nom1, nom2);
	public MemoriaCuatroDosJugadores() {
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
					if (cartas[i].valor == temporal.valor) {
						cartas[i].revelado = true;
						if(numJugador == 1){
						}else{
						}
						boolean bandera = true;
						for (Carta elemento : cartas) {
							if (elemento.revelado == false) {
								bandera = false;
								break;
							}
						}
						if (bandera) {
							if (punt1>punt2) {
								JOptionPane.showMessageDialog(this, "Felicidades" + nom1 +"Ganaste, El puntaje es: " + punt1);
							} else {
								JOptionPane.showMessageDialog(this, "Felicidades" + nom2 + "Ganaste, El puntaje es: " + punt2);
							}
						}

					} else {
						if (numJugador == 1) {
							JOptionPane.showMessageDialog(null, "Turno de jugador:"+ nom2);
							numJugador = 2;
						}else{
							JOptionPane.showMessageDialog(null, "Turno de jugador:"+ nom1);
							numJugador=1;
						}
						try {
							cartas[i].btn.update(cartas[i].btn.getGraphics());
							Thread.sleep(500);
							tapar(i);
						} catch (Exception e2) {
							System.out.println(e2);
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
	public static int ElegirJugador(String nombre1, String nombre2) {
		int jugadorActual = 0;
		int numeroAleatorio = (int) (Math.random() * 2);
		if (numeroAleatorio == 1) {
			JOptionPane.showMessageDialog(null, "Inicia el usuario: " + nombre1);
		} else {
			JOptionPane.showMessageDialog(null, "Inicia el usuario: " + nombre2);
		}
		numeroAleatorio = jugadorActual;
		return numeroAleatorio;
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
	public static String pedirNombreSegundo(){
		String nom2 = JOptionPane.showInputDialog("Nombre de usuario ");
		System.out.println(nom2);
		return nom2;
	}

}
