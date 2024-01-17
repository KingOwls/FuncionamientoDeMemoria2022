package trabajoFundamentado;

import java.util.Scanner;

import javax.swing.JOptionPane;


public class PrincipalTotal {
	
public static void main (String[] args) {
	
	int modoJuego= 0;
	int DificultadJuego= 0;
	int modalidadJuego= 0;
	Scanner scan = new Scanner(System.in);
	//hacer un swich con 9 casos, donde cada uno esta la seleccion en orden.
	DificultadJuego = Integer.parseInt(JOptionPane.showInputDialog("seleccione una dificultad de juego: 1 = para 4x4, 2 = para 6x6, 3 = para 8x8"));
	modalidadJuego = Integer.parseInt(JOptionPane.showInputDialog("seleccione una modalidad de juego: 0 = para solitario, 3 = para jucar contra la maquina, 6 para dos jugadores"));
	modoJuego = DificultadJuego + modalidadJuego;
		switch (modoJuego) {
		//se va a seleccionar dos botones, solo, maquina, dos jugadores.
				//solo = mododejuego +0;
				//maquina = mododejuego +3S
				//dos jugadores = mododejuego +6
		//seleccion orden:
				//memoria de 4, 6 a 8
		//si escoje un numero del uno al tres, es la dificultad.
		
		case 1:
			new Memoria().setVisible(true);
			break;
		case 2:
			new MemoriaParaSeis().setVisible(true); 
			break;
		case 3:
			new MemoriaDeOcho().setVisible(true);
			break;
			//memoria maquina 4, 6, 8,	
			//si escoje de 4 a 6, selecciona dificultad con maquina
		case 4:
			new MemoriaCuatroComputadora().setVisible(true);
			break;
		case 5:
			new MemoriaSeisComputadora().setVisible(true);
			break;
		case 6:
			new MemoriaOchoComputadora().setVisible(true);
			break;
			//memoria dos jugadores 4, 6, 8,
			//si escoge de 7 a 9, selecciona dificultad con dos jugadores
		case 7:
			new MemoriaCuatroDosJugadores().setVisible(true);
			break;
		case 8:
			new MemoriaSeisDosJugadores().setVisible(true);
			break;
		case 9:
			new MemoriaOchoDosJugadores().setVisible(true);
			break;
		}
	
	}

}
