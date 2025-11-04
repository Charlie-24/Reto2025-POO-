package Batalla;

import java.util.Random;
import java.util.Scanner;

public class Batalla {
	
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		boolean exit=false;
		
		int ataqueAcertado=0;
		int ataqueFallado=0;
		int vidaHeroe=100;
		int vidaDragon=120;
	
		System.out.println("Introduce el nombre de tu Héroe");
		String nombre=sc.nextLine();
		
		System.out.println(nombre+" bienbenido al campo de batalla");
		
		do {
		
			System.out.println("\n***Turno del Heroe ( "+nombre+" )***");
			
			System.out.println("Salud Héroe");
			mostrarVida(vidaHeroe);
			
			System.out.println("\n---Ataques---");
			System.out.println("1. Ataque ligero (90%) 10-20 HP");
			System.out.println("2. Ataque fuerte (60%) 25-35 HP");
			System.out.println("3. Ataque mágico (40%) 40-50 HP");
			int opcion=Integer.parseInt(sc.nextLine());
			int daño=switch(opcion) {
			case 1->{
				int d=calcularAtaque(90,10,20);
				yield d;
			}
			case 2->{
				int d=calcularAtaque(60,25,35);
				yield d;	
			}
			case 3->{
				int d=calcularAtaque(40,40,50);
				yield d;
			}
			default ->{ 
				yield 0;
			}
			};
			vidaDragon-=daño;
			
			if(daño==0)
				ataqueFallado++;
			else
				ataqueAcertado++;
			
			
			
			System.out.println("\n***Turno del dragon***");
			
			System.out.println("Salud Dragon");
			mostrarVida(vidaDragon);
			
			vidaHeroe-=calcularAtaque(70,15,30);
			
			System.out.println();
			
			if(vidaDragon<=0) {
				System.out.println("\nVICTORIA!!!");
				System.out.println("Has derrotado al dragon");
				exit=true;
			}
			else if(vidaHeroe<=0) {
				System.out.println("\nDERROTA!!!");
				System.out.println("El dragon ha ganado");
				exit=true;
			}
			
		}while(!exit);
		
		System.out.println("Heroe jugado: ( "+nombre+" )");
		System.out.println("Vida Restante Héroe: ");
		mostrarVida(vidaHeroe);
		System.out.println("\nVida Restante Dragon: ");
		mostrarVida(vidaDragon);
		System.out.println("\nAtaques realizados: "+(ataqueFallado+ataqueAcertado));
		System.out.println("Ataques fallados: "+ataqueFallado);
		System.out.println("Ataque acertado: "+ataqueAcertado);
		
		
		
		
	}
	
	private static int calcularAtaque(int probabilidad, int HPmin, int HPmax) {
		
		Random r=new Random();
		
		int porcentaje=r.nextInt(1,101);
		
		if(porcentaje>probabilidad) {
			System.out.println("\nAtaque fallado");
			return 0;
		}
		else {
			System.out.print("\nAtaque realizado con exito: ");
			int HPinfligido=r.nextInt(HPmin,HPmax+1);
			System.out.print("-"+HPinfligido);
			return HPinfligido;
		}
		
	}

	private static void mostrarVida(int vida) {
		
		System.out.print("[");
		for(int i=0;i<vida;i+=10) {
			
			System.out.print("-");
		}
		System.out.print("]");
		
	}

}
