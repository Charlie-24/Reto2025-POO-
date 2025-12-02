package Batalla;

import java.util.Random;

public class Dragon {
	
	
	
	private String nombre;
	private int puntosVida;
	private static int AtaquesFallados;
	private static int AtaquesAcertados;
	private int fase;
	
	private static final String[] NOMBRES_FASES = {"Joven", "Adulto", "Anciano"};
	private static final int[] VIDA_MIN = { 100, 120, 140 };
	private static final int[] VIDA_MAX = { 120, 140, 160 };
	private static final int[] PROB_ACIERTO = { 70, 75, 80 };
	private static final int[] DANIO_MIN = { 15, 20, 30 };
	private static final int[] DANIO_MAX = { 30, 35, 40 };
	
	
	public Dragon() {
		Random r=new Random();
		this.fase=0;
		this.nombre =NOMBRES_FASES[fase];
		this.puntosVida=r.nextInt(VIDA_MIN[fase],VIDA_MAX[fase]+1);
		
		AtaquesFallados=0;
		AtaquesAcertados=0;
	}
	
	
	public static String getNombresFases(int i) {
		return NOMBRES_FASES[i];
	}


	public int getFase() {
		return fase;
	}


	public void setFase(int fase) {
		
		Random r=new Random();
		
		this.fase=fase;
		this.nombre =NOMBRES_FASES[fase];
		this.puntosVida=r.nextInt(VIDA_MIN[fase],VIDA_MAX[fase]+1);
		
		
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
	
	public int Atacar() {
		
		return calcularAtaque(PROB_ACIERTO[fase],DANIO_MIN[fase],DANIO_MAX[fase]);
	
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
		
		if(puntosVida<=0)
			return false;
		else
			return true;
		
	}

}
