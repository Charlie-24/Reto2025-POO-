package Batalla;

import java.util.Random;

public class Heroe {

	private String nombre;
	private int puntosVida;
	private static int AtaquesFallados=0;
	private static int AtaquesAcertados=0;

	private static final String[] NOMBRES_ATAQUES = { "Ligero", "Fuerte", "Mágico" };
	private static final int[] PROB_ACIERTO = { 90, 60, 40 };
	private static final int[] DANIO_MIN = { 10, 25, 40 };
	private static final int[] DANIO_MAX = { 20, 35, 50 };

	public Heroe(String nombre) {
		
		this.nombre = nombre;
		this.puntosVida = 100;
		
		AtaquesFallados=0;
		AtaquesAcertados=0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	public int getAtaquesFallados() {
		return AtaquesFallados;
	}

	public void setAtaquesFallados(int ataquesFallados) {
		AtaquesFallados = ataquesFallados;
	}

	public int getAtaquesAcertados() {
		return AtaquesAcertados;
	}

	public void setAtaquesAcertados(int ataquesAcertados) {
		AtaquesAcertados = ataquesAcertados;
	}

	public int Atacar(int i) {

		return calcularAtaque(PROB_ACIERTO[i - 1], DANIO_MIN[i - i], DANIO_MAX[i - 1]);
		
	}

	private static int calcularAtaque(int probabilidad, int HPmin, int HPmax) {

		Random r = new Random();

		int porcentaje = r.nextInt(1, 101);

		if (porcentaje > probabilidad) {
			System.out.println("\nAtaque fallado");
			AtaquesFallados++;
			return 0;
		} else {
			System.out.print("\nAtaque realizado con exito: ");
			int HPinfligido = r.nextInt(HPmin, HPmax + 1);
			System.out.print("-" + HPinfligido);
			AtaquesAcertados++;
			return HPinfligido;
		}
	}

	public void RecibirDanyo(int danyo) {
		
		puntosVida-=danyo;

	}

	public boolean estaVivo() {
		if (puntosVida <= 0)
			return false;
		else
			return true;
	}

}
