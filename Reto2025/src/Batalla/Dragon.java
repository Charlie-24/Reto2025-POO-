package Batalla;

import java.util.Random;

public class Dragon {
	
	private String nombre_fase;
	private String nombre;
	private int puntosVida;
	private static int AtaquesFallados;
	private static int AtaquesAcertados;
	private static int fase;
	
	private static final String[] NOMBRES_FASES = {"Joven", "Adulto", "Anciano"};
	private static final String[] NOMBRES_DRAGONES = {"Drakthor","Ignavar","Velaryn","Velaryn","Kaelzara"};
	private static final int[] VIDA_MIN = { 100, 120, 140 };
	private static final int[] VIDA_MAX = { 120, 140, 160 };
	
	private static final int[][] DATOS_ATAQUES = {
						{70,15,30},
						{75,20,35},  //Probabilidad, Daño minimo, Daño maximo
						{80,30,40}
	};
	
	public Dragon(int fase) {
		Random r=new Random();
		int nombreAleatorio=r.nextInt(NOMBRES_DRAGONES.length+1);
		this.fase=fase;
		this.nombre=NOMBRES_DRAGONES[nombreAleatorio];
		this.nombre_fase =NOMBRES_FASES[fase];
		this.puntosVida=r.nextInt(VIDA_MIN[fase],VIDA_MAX[fase]+1);
		
		AtaquesFallados=0;
		AtaquesAcertados=0;
	}
	
	
	public static String getNombresFases() {
		return NOMBRES_FASES[fase];
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
		int index=0;
		return calcularAtaque(DATOS_ATAQUES[fase][index++],DATOS_ATAQUES[fase][index++],DATOS_ATAQUES[fase][index]);
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
