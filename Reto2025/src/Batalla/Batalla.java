package Batalla;

import java.util.ArrayList;
import java.util.Scanner;

public class Batalla {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        
        ArrayList<String> acciones=new ArrayList<>();
        
       // 1. Ataques realizados
       // 2. Fallos
       // 3. Derrota de un dragón
       // 4. Inicio de una nueva fase

             
        int MAX_FASES = 3; // número total de fases del dragón

        Dragon[] DragonesDeBatalla =new Dragon[MAX_FASES];
      
        MAX_FASES-=1;
        	
        int faseUltima = 0;
        int DañoTotal=0;
        	
        System.out.println("Introduce el nombre de tu Héroe");
        Heroe heroe = new Heroe(sc.nextLine());

        System.out.println(heroe.getNombre() + "¡¡ Bienvenido al campo de batalla !!");

        // Iteramos fases con for
        
        for (int fase = 0; fase <= MAX_FASES && heroe.estaVivo(); fase++) {
            
        	//Variable para controlar las fases
        	faseUltima=fase;
        	
        	DragonesDeBatalla[fase]=new Dragon(fase);
        
            System.out.println("\nHa aparecido un dragon " + DragonesDeBatalla[fase].getNombresFases()+" ,"+DragonesDeBatalla[fase].getNombre());
            System.out.println("Salud Dragon");
            mostrarVida(DragonesDeBatalla[fase].getPuntosVida());
            System.out.print(" " + DragonesDeBatalla[fase].getPuntosVida() + "\n");

            // Bucle de la fase: turno hasta que muera el dragon o el héroe
            while (DragonesDeBatalla[fase].estaVivo() && heroe.estaVivo()) {
                // Turno héroe
            	
            	System.out.println("\nSalud Héroe");
                mostrarVida(heroe.getPuntosVida());
                System.out.print(" " + heroe.getPuntosVida() + "\n");
                
                System.out.println("\n* * *Turno del Héroe ( " + heroe.getNombre() + " )* * *");
               
                System.out.println("\n---Ataques---");
                System.out.println("1. Ataque ligero (90%) 10-20 HP");
                System.out.println("2. Ataque fuerte (60%) 25-35 HP");
                System.out.println("3. Ataque mágico (40%) 40-50 HP");
                System.out.print("Elige ataque (1-3): ");

                int opcion;
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                    if (opcion < 1 || opcion > 3) opcion = 1;
                } catch (NumberFormatException e) {
                    opcion = 1; // por defecto ataque ligero si input no válido
                }

                int danyoADragon = heroe.Atacar(opcion);
                DragonesDeBatalla[fase].RecibirDanyo(danyoADragon);
                DañoTotal+=danyoADragon;
                if(danyoADragon ==	0)
                	acciones.add("Ataque fallado");
                else 
                	acciones.add("Ataque realizado");

                // Si matamos al dragón, salimos del while para pasar a la siguiente fase
                if (!DragonesDeBatalla[fase].estaVivo()) { 
                	acciones.add("Dragon Derrotado");
                	break;
                }

                // Turno dragón
                System.out.println("\n* * *Turno del dragon* * *");
                System.out.println("Salud Dragon");
                mostrarVida(DragonesDeBatalla[fase].getPuntosVida());
                System.out.print(" " + DragonesDeBatalla[fase].getPuntosVida() + "\n");

                int danyoAHeroe = DragonesDeBatalla[fase].Atacar();
                heroe.RecibirDanyo(danyoAHeroe);
            }

            // Resultado de la fase
            if (!heroe.estaVivo()) {
                System.out.println("\n    !!!DERROTA!!!");
                System.out.println("El dragon " + DragonesDeBatalla[fase].getNombresFases()+ " ,"+DragonesDeBatalla[fase].getNombre()+", ha ganado");
                break; // sale del for porque el héroe murió
            }

            if (!DragonesDeBatalla[fase].estaVivo()) {
                System.out.println("\nHas derrotado al dragon " + DragonesDeBatalla[fase].getNombresFases());
                if (fase < MAX_FASES) {
                    System.out.println("Ha aparecido una nueva fase. Tu héroe se cura.");
                    acciones.add("Nueva Fase");
                    heroe.setPuntosVida(100); // cura entre fases
                } else {
                    System.out.println("\n    !!!VICTORIA!!!");
                    System.out.println("Has acabado con todas las fases del dragon");
                }
            }
        } // fin for fases

        // Estadísticas finales
        System.out.println("\nHeroe jugado: ( " + heroe.getNombre() + " )");
        System.out.println("Vida Restante Héroe: ");
        mostrarVida(heroe.getPuntosVida());
        System.out.print(" " + heroe.getPuntosVida() + "\n");

        System.out.println("\nAtaques realizados: " + (heroe.getAtaquesAcertados() + heroe.getAtaquesFallados()));
        System.out.println("Ataques fallados: " + heroe.getAtaquesFallados());
        System.out.println("Ataque acertado: " + heroe.getAtaquesAcertados());
        System.out.println("Turnos: "+heroe.getAtaquesAcertados());
        System.out.println("Daño Total: "+DañoTotal);
        System.out.println(" --Dragones Derrotados--");
       
   
        if(faseUltima!=0) {
        	faseUltima-=1;
        	while(faseUltima!=-1) 
        	System.out.print(DragonesDeBatalla[faseUltima--].getNombre()+" ");
        	
        }
        else 
        	System.out.println("Ningun dragon derrotado");
        System.out.println("\n --Acciones del Heroe--");
        System.out.println(acciones);
        sc.close();
    }

    private static void mostrarVida(int vida) {
        // pinta una barra simple por cada 10 HP
    		
        System.out.print("[");
        int barras = Math.max(0, vida / 10);
        for (int i = 0; i < barras; i++) {
            System.out.print("-");
        }
        System.out.print("]");
    }
    
    
}

