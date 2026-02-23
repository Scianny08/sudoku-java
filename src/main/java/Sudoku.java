import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
    	
    	Scanner t = new Scanner(System.in);
    	int azione;
    	
    	System.out.println("\n--- Benvenuto al SUDOKU made by Scianny ---\n");
    	
    	do {
    		System.out.println("-- Menu iniziale --\n");
    		
    		System.out.println("Scegli un'azione (0-1): ");
    		System.out.println("0 - Esci dal gioco");
        	System.out.println("1 - Nuova partita");
        	
        	do {
	    		System.out.print("\nInserisci: ");
	    		while(!t.hasNextInt()) {
	    			System.out.println("Opzione non valida.");
	    			System.out.print("\nInserisci: ");
	    			t.next();
	    		}
	    		azione = t.nextInt();
	    		t.nextLine();
	    		
	    		if (azione != 0 && azione != 1) {
	    			System.out.println("Opzione non valida.");
	    		}
        	} while (azione != 0 && azione != 1);
        	
        	System.out.println();
        	
        	if (azione == 1) {
        		Partita partita = new Partita();
            	LogicaPartita logica = partita.getLogica();
            	
    			logica.generaSudoku();
    	        
    	        //impostazioni gioco
    	        int difficolta = 0;
    	        double visibilita = 0;
    	        int erroriMax;
    			
    	        System.out.println("Scegli una difficoltà (1-5): ");
    	        System.out.println("1 - Facile\t\t(60% completato)");
    	        System.out.println("2 - Media\t\t(50% completato)");
    	        System.out.println("3 - Difficile\t\t(30% completato)");
    	        System.out.println("4 - Impossibile\t\t(20% completato)");
    	        System.out.println("5 - Personalizzata\n");
    	        
    	        do {
    	        	System.out.print("Inserisci: ");
    	        	while (!t.hasNextInt()) {
        			    System.out.println("Valore non valido.\n");
        			    System.out.print("Inserisci: ");
        			    t.next();
        			}
    	        	difficolta = t.nextInt();
    	        	t.nextLine();
    	        	
					if (difficolta < 1 || difficolta > 5) {
						System.out.println("Valore fuori range.\n");
    	        	}
    	        } while (difficolta < 1 || difficolta > 5);
    	        
    	        if (difficolta == 5) {
	        		do {
	        			System.out.print("Percentuale % di celle coperte: ");
	        			while (!t.hasNextDouble()) {
	        			    System.out.println("Valore non valido.\n");
	        			    System.out.print("Percentuale % di celle coperte: ");
	        			    t.next();
	        			}
	        			visibilita = t.nextDouble();
	        			t.nextLine();
	        			
	        			if (visibilita < 0 || visibilita > 100) {
	        				System.out.println("Valore fuori range.\n");
	        			}
	        		} while (visibilita < 0 || visibilita > 100);
	        		
	        		do {
	        			System.out.print("Errori permessi (1-1000): ");
	        			while (!t.hasNextInt()) {
	        			    System.out.println("Valore non valido.\n");
	        			    System.out.print("Errori permessi (1-1000): ");
	        			    t.next();
	        			}
	        			erroriMax = t.nextInt();
	        			t.nextLine();
	        			
	        			if (erroriMax < 1 || erroriMax > 1000) {
	        				System.out.println("Valore fuori range.\n");
	        			}
	        		} while (erroriMax < 1 || erroriMax > 1000);
	        		
	        		logica.generaSudokuIrrisolto(visibilita, erroriMax);
    	        } else {
    	        	logica.generaSudokuIrrisolto(difficolta);
    	        }

    	        
    	        //gioco vero e proprio
    	        System.out.print("\nPremi invio per GIOCARE!");
	        	t.nextLine();
	        	
	        	if (visibilita != 1) System.out.println();
	        	
    	        boolean confermato;
    	        boolean uguali;
    	        int rigaSel, colonnaSel = -1;
    	        int num;
    	        int i;
    	        int esitoMossa = 10;
    	        
    	        long inizioTempo = System.currentTimeMillis();
    	        
    	        uguali = partita.areGriglieUguali();
    	        
    	        while(partita.checkErrori() && !uguali) {
    	        	for (i=0; i<50; i++) System.out.print("-");
    	        	
    	        	System.out.println("\n\nErrori: "+ partita.getErrori() +"/"+ logica.getDifficolta().getErroriMax() +"\n");
    	        	
    	        	System.out.println(logica.getGrigliaIrrisolta().toString() +"\n");
    	        	
    	        	//numero di numeri
    	        	System.out.println(logica.numMancantiToString() +"\n");
    	        	
    	        	do {
    	        		confermato = true;
    	        		
    	        		do {
        	        		System.out.print("Seleziona un numero: ");
        	        		while(!t.hasNextInt()) {
        		    			System.out.println("Valore non valido.\n");
        		    			System.out.print("Seleziona un numero: ");
        		    			t.next();
        		    		}
        	        		num = t.nextInt();
        	        		t.nextLine();
        	        		
        	        		if (num < 1 || num > 9) {
        	        			System.out.println("Valore fuori range.\n");
        	        		}
        	        	} while (num < 1 || num > 9);
        	        	
        	        	
        	        	System.out.println("Seleziona la posizione del numero (0 per annullare l'inserimento):");
        	        	do {
        	        		System.out.print("RIGA: ");
        	        		while(!t.hasNextInt()) {
        	        			System.out.println("Valore non valido.");
        		    			System.out.print("RIGA: ");
        		    			t.next();
        		    		}
        	        		rigaSel = t.nextInt();
        	        		t.nextLine();
        	        		
        	        		if (rigaSel < 0 || rigaSel > 9) {
        	        			System.out.println("Valore fuori range.");
        	        		} else if (rigaSel == 0) {
        	        			confermato = false;
        	        		}
        	        		
        	        	} while (rigaSel < 0 || rigaSel > 9);
        	        	
        	        	if (confermato) {
        	        		do {
            	        		System.out.print("COLONNA: ");
            	        		while(!t.hasNextInt()) {
            	        			System.out.println("Valore non valido.");
            		    			System.out.print("COLONNA: ");
            		    			t.next();
            		    		}
            	        		colonnaSel = t.nextInt();
            	        		t.nextLine();
            	        		
            	        		if (colonnaSel < 0 || colonnaSel > 9) {
            	        			System.out.println("Valore fuori range.");
            	        		} else if (colonnaSel == 0) {
            	        			confermato = false;
            	        		}
            	        		
            	        	} while (colonnaSel < 0 || colonnaSel > 9);
        	        	}
        	        	
        	        	if (confermato) {
        	        		esitoMossa = partita.mossa(num, rigaSel, colonnaSel);
        	        		if (esitoMossa == -1) {
            	        		System.out.println("Casella già occupata!");
            	        		confermato = false;
            	        	}
        	        	}
        	        	
        	        	if (!confermato) {
        	        		System.out.println();
        	        	}
        	        	
        	        	
    	        	} while (!confermato);
    	        	
    	        	System.out.println();
    	        	if (esitoMossa == num) System.out.println("Giusto!");
    	        	else System.out.println("Errato!");
    	        	
    	        	uguali = partita.areGriglieUguali();
    	        	
    	        	if (!uguali) {
    	        		System.out.println("\nPremere invio per continuare.");
        	        	t.nextLine();
    	        	}
    	        	
    	        } //fine loop partita
    	        
    	        System.out.println();
    	        for (i=0; i<50; i++) System.out.print("-");
    	        System.out.println("\n");
    	        
    	        if (!partita.checkErrori()) {
    	        	System.out.println("Hai perso.");
    	        }
    	        if (uguali) {
    	        	System.out.println(logica.getGriglia().toString() +"\n");
    	        	System.out.println("Hai vinto!");
    	        }
    	        
    	        
    	        long fineTempo = System.currentTimeMillis();
    	        long millisecondiTotali = fineTempo - inizioTempo;
    	        
    	        long secondiTotali = millisecondiTotali / 1000;
    	        long minuti = secondiTotali / 60;
    	        long secondi = secondiTotali % 60;
    	        
    	        System.out.println("Tempo di gioco: " + minuti + "m " + secondi + "s");
    	        
    	        
    	        System.out.println("\nPremere invio per continuare.");
	        	t.nextLine();
	        	
	        	
    	        for (i=0; i<50; i++) System.out.print("-");
    	        System.out.println("\n");
    	        
        	} //fine azione = 1
        	
    	} while (azione != 0); //fine loop gioco
    	
    	System.out.println("Grazie per aver giocato!");
		t.close();
		
    } //fine main
    
} //fine classe
