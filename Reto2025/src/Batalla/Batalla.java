package Batalla;

import java.util.Random;
import java.util.Scanner;

public class Batalla {
	
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		boolean exit=false;
		int fase=0;
	
		System.out.println("Introduce el nombre de tu Héroe");
		Heroe heroe=new Heroe(sc.nextLine());
		
		System.out.println(heroe.getNombre()+" bienbenido al campo de batalla");
		
		Dragon dragon=new Dragon();
		
		System.out.println("Ha aparecido un dragon el cual debes derrotar");
		
		System.out.println("Salud Dragon");
		mostrarVida(dragon.getPuntosVida());
		System.out.print(" "+dragon.getPuntosVida());
		
		do {
		
			System.out.println("\n***Turno del Heroe ( "+heroe.getNombre()+" )***");
			
			System.out.println("Salud Héroe");
			mostrarVida(heroe.getPuntosVida());
			System.out.print(" "+heroe.getPuntosVida());
			
			System.out.println("\n---Ataques---");
			System.out.println("1. Ataque ligero (90%) 10-20 HP");
			System.out.println("2. Ataque fuerte (60%) 25-35 HP");
			System.out.println("3. Ataque mágico (40%) 40-50 HP");
			
			//Opcion de ataque 
			int opcion=Integer.parseInt(sc.nextLine());
			
			//Dragon recibe daño de ataque indicado
			int danyoADragon=heroe.Atacar(opcion);
			dragon.RecibirDanyo(danyoADragon);
			
			
			System.out.println("\n***Turno del dragon***");
			
			System.out.println("Salud Dragon");
			mostrarVida(dragon.getPuntosVida());
			System.out.print(" "+dragon.getPuntosVida());
			
			//Heroe recibe daño de dragon
			int danyoAHeroe=dragon.Atacar();
			heroe.RecibirDanyo(danyoAHeroe);
			
			System.out.println();
			
			if(!dragon.estaVivo()) {
				System.out.println("Has derrotado al dragon "+Dragon.getNombresFases(dragon.getFase()));
				System.out.println("Ha aparecido una nueva fase");
				
				//Fase 1, El heroe se cura
				dragon.setFase(fase++);
				heroe.setPuntosVida(100);
			
				}
				if(fase==2) {
					System.out.println("\nVICTORIA!!!");
					System.out.println("Has acabado con todas las fases del dragon ");
					exit=true;
				}
			
			
			else if(!heroe.estaVivo()) {
				System.out.println("\nDERROTA!!!");
				System.out.println("El dragon fase "+Dragon.getNombresFases(fase)+" ha ganado");
				exit=true;
			}
			
		}while(!exit);
		
		System.out.println("Heroe jugado: ( "+heroe.getNombre()+" )");
		System.out.println("Vida Restante Héroe: ");
		mostrarVida(heroe.getPuntosVida());
		System.out.print(" "+heroe.getPuntosVida());
	
		System.out.println("\nVida Restante Dragon: ");
		mostrarVida(dragon.getPuntosVida());
		System.out.print(" "+dragon.getPuntosVida());
		
		System.out.println("\nAtaques realizados: "+(heroe.getAtaquesAcertados()+heroe.getAtaquesFallados()));
		System.out.println("Ataques fallados: "+heroe.getAtaquesFallados());
		System.out.println("Ataque acertado: "+heroe.getAtaquesAcertados());
		
		
		
		
	}
	

	private static void mostrarVida(int vida) {
		
		System.out.print("[");
		for(int i=0;i<vida;i+=10) {
			
			System.out.print("-");
		}
		System.out.print("]");
		
	}

}
