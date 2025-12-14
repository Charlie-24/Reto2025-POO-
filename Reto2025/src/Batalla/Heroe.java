package Batalla;

import java.util.Random;

public class Heroe {

	private String nombre;
	private int puntosVida;
	private static int AtaquesFallados=0;
	private static int AtaquesAcertados=0;

	private static final String[] NOMBRES_ATAQUES = { "Ligero", "Fuerte", "Mágico" };
	private static final int[][] DATOS_ATAQUES = {
			 	{90, 10, 20},  
			    {60, 25, 35},	// Probabilidad, daño mínimo, daño máximo
			    {40, 40, 50}
			};

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
		if(puntosVida<0)
			puntosVida=0;
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
		int index=0;
		return calcularAtaque(DATOS_ATAQUES[i - 1][index++], DATOS_ATAQUES[i - 1][index++], DATOS_ATAQUES[i - 1][index]);
		
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
		
		return puntosVida>0;
	}

}
