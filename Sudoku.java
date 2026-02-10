import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
    	
    	Scanner t = new Scanner(System.in);
    	char azione;
    	
    	System.out.println("\n--- Benvenuto a SUDOKU made by Scianny ---\n");
    	
    	do {
    		System.out.println("Scegli un'azione (0-1): ");
    		System.out.println("0 - Esci dal gioco");
        	System.out.println("1 - Nuova partita");
        	
        	do {
	    		System.out.print("\nInserisci: ");
	    		azione = t.next().charAt(0);
	    		
	    		if (azione != '0' && azione != '1') {
	    			System.out.println("Opzione non valida.");
	    		}
        	} while (azione != '0' && azione != '1');
        	
        	System.out.println();
        	
        	if (azione == '1') {
    			//grandezza sudoku
    	        final int DIM = 9;

    	        //creazione sudoku
    	        int[][] sudoku = new int[DIM][DIM];

    	        //contatori
    	        int rig, col, i, tentativi, x, y;
    	        
    	        //variabili d'appoggio
    	        int nuovaCella = 0;
    	        int inizioR, inizioC;
    	        
    	        //booleani
    	        boolean trovato;
    	        
    	        /*
    	         * LOGICA PER LA GENERAZIONE AUTOMATICA DI UN SUDOKU (9x9)
    	         * -------------------------------------------------------
    	         * 1. STRUTTURA DATI: Utilizzare una matrice bidimensionale di interi 
    	         * di dimensione 9x9 per rappresentare la griglia (81 celle).
    	         *
    	         * 2. INIZIALIZZAZIONE: Riempire l'intera matrice con il valore 0. 
    	         * Lo zero indica che la cella è "vuota" e disponibile.
    	         *
    	         * 3. CICLO DI RIEMPIMENTO: Scorrere la matrice cella per cella utilizzando 
    	         * due cicli 'for' annidati (uno per le righe, uno per le colonne).
    	         *
    	         * 4. GENERAZIONE CASUALE: Per ogni cella, generare un numero random tra 1 e 9.
    	         *
    	         * 5. VALIDAZIONE (LE 3 REGOLE): Prima di confermare il numero, verificare:
    	         * - ANTI-RIGA: Il numero non deve già esistere nella riga corrente.
    	         * - ANTI-COLONNA: Il numero non deve già esistere nella colonna corrente.
    	         * - ANTI-QUADRATO: Il numero non deve esistere nel sotto-quadrato 3x3.
    	         * (Calcolare i confini del quadrato usando: riga - (riga % 3) e colonna - (colonna % 3)).
    	         *
    	         * 6. GESTIONE DEI VICOLI CIECHI (BACKTRACKING):
    	         * Se dopo un certo numero di tentativi (es. 35) non si trova un numero valido:
    	         * - Resettare (portare a 0) tutti i valori della riga corrente.
    	         * - Decrementare l'indice della riga per tornare a quella precedente e riprovare.
    	         * Questo evita che il generatore rimanga bloccato in una configurazione impossibile.
    	         *
    	         */
    	        
    	        //STEP 1
    	        for (rig=0; rig<DIM; rig++) {
    	        	for (col=0; col<DIM; col++) {
    	        		sudoku[rig][col] = 0;
    	        	}
    	        }
    	        
    	        //STEP 2-3
    	        for (rig=0; rig<DIM; rig++) {
    	        	for (col=0; col<DIM; col++) {
    	        		
    	        		tentativi = 0;
    	        		
    	        		do {
    	        			trovato = false;
    	        			nuovaCella = (int)(Math.random()*9+1); //STEP 4
    	        			
    	        			//STEP 5
    	        			//ricerca sulle righe
    	        			i=0; while(i<col && !trovato) {
    	        				if (nuovaCella == sudoku[rig][i]) {
    	        					trovato = true;
    	        				}
    	        				i++;
    	        			}
    	        			
    	        			//ricerca sulle colonne
    		    			i=0; while(i<rig && !trovato) {
    		    				if (nuovaCella == sudoku[i][col]) {
    		    					trovato = true;
    		    				}
    		    				i++;
    		    			}
    	        			
    		    			/*
    		    			 * TRACE CALCOLO INIZIO SOTTOMATRICE (3x3)
    		    			 * ------------------------------------------------------------
    		    			 * FORMULA: inizio = indice - (indice % 3)
    		    			 * * INDICE  |  MODULO (%) |  SOTTRAZIONE |  RISULTATO (INIZIO)
    		    			 * ----------|-------------|--------------|--------------------
    		    			 *      0    |  0 % 3 = 0  |    0 - 0     |         0
    		    			 *      1    |  1 % 3 = 1  |    1 - 1     |         0
    		    			 *      2    |  2 % 3 = 2  |    2 - 2     |         0
    		    			 * ----------|-------------|--------------|--------------------
    		    			 *      3    |  3 % 3 = 0  |    3 - 0     |         3
    		    			 *      4    |  4 % 3 = 1  |    4 - 1     |         3
    		    			 *      5    |  5 % 3 = 2  |    5 - 2     |         3
    		    			 * ----------|-------------|--------------|--------------------
    		    			 *      6    |  6 % 3 = 0  |    6 - 0     |         6
    		    			 *      7    |  7 % 3 = 1  |    7 - 1     |         6
    		    			 *      8    |  8 % 3 = 2  |    8 - 2     |         6
    		    			 * ------------------------------------------------------------
    		    			 */
    		    			inizioR = rig - (rig % 3);
    		    			inizioC = col - (col % 3);
    		    			
    		    			x = inizioR;
    		    			while (x < inizioR + 3 && !trovato) {
    		    			    y = inizioC;
    		    			    while (y < inizioC + 3 && !trovato) {
    		    			        if (nuovaCella == sudoku[x][y]) {
    		    			            trovato = true;
    		    			        }
    		    			        y++;
    		    			    }
    		    			    x++;
    		    			}
    		    			tentativi++;
    	        		} while (trovato && tentativi <= 100);
    	        		
    	        		//STEP 6
    	        		if (tentativi < 100) {
    	        			sudoku[rig][col] = nuovaCella;
    	        		} else {
    	        			for (i=0; i<DIM; i++) {
    	        				sudoku[rig][i] = 0;
    	        			}
    	        			
    	        			rig--; if (rig < 0) rig = 0;
    	    				col = -1;
    	        		}
    	        		
    	        	} //fine for colonne
    	        	
    	        } //fine for righe
    	        
    	        
    	        //impostazioni gioco
    	        char difficolta;
    	        int erroriMax = 0;
    	        double visibilita = 1;
    	        
    	        System.out.println("Scegli una difficoltà (1-5): ");
    	        System.out.println("1 - Facile\t\t(60% completato)");
    	        System.out.println("2 - Media\t\t(50% completato)");
    	        System.out.println("3 - Difficile\t\t(30% completato)");
    	        System.out.println("4 - Impossibile\t\t(20% completato)");
    	        System.out.println("5 - Personalizzata");
    	        
    	        do {
    	        	System.out.print("\nInserisci: ");
    	        	difficolta = t.next().charAt(0);
					if (difficolta < '1' || difficolta > '5') {
						System.out.println("Opzione non valida.");
    	        	}
    	        } while (difficolta < '1' || difficolta > '5');
    	        
    	        switch (difficolta) {
    	        	case '1':
    	        		visibilita = 0.4;
    	        		erroriMax = 5;
    	        	break;
    	        	
    	        	case '2':
    	        		visibilita = 0.5;
    	        		erroriMax = 3;
    	        	break;
    	        	
    	        	case '3':
    	        		visibilita = 0.7;
    	        		erroriMax = 2;
    	        	break;
    	        	
    	        	case '4':
    	        		visibilita = 0.8;
    	        		erroriMax = 1;
    	        	break;
    	        	
    	        	case '5':
    	        		do {
    	        			System.out.print("Percentuale % di celle coperte: ");
    	        			while (!t.hasNextDouble()) {
    	        			    System.out.println("Valore non valido. Riprova: ");
    	        			    t.next();
    	        			}
    	        			visibilita = t.nextDouble();
    	        		} while (visibilita < 0 || visibilita > 100);
    	        		
    	        		visibilita = visibilita / 100;
    	        		
    	        		do {
    	        			System.out.print("Errori permessi: ");
    	        			while (!t.hasNextInt()) {
    	        			    System.out.println("Valore non valido. Riprova: ");
    	        			    t.next();
    	        			}
    	        			erroriMax = t.nextInt();
    	        		} while (erroriMax < 1 || erroriMax > 1000);
    	        		
    	        	break;
    	        }
    	        
    	        
    	        int[][] sudokuIrrisolto = new int[DIM][DIM];
    	        for (rig=0; rig<DIM; rig++) {
    	        	for (col=0; col<DIM; col++) {
    	        		sudokuIrrisolto[rig][col] = sudoku[rig][col];
    	        	}
    	        }
    	        
    	        
    	        int celleCoperte = (int)(DIM*DIM*visibilita);
    	        
    	        for(i=0; i<celleCoperte; i++) {
    				do {
    					rig = (int)(Math.random()*9);
    					col = (int)(Math.random()*9);
    				} while(sudokuIrrisolto[rig][col] == 0);
    				
    				sudokuIrrisolto[rig][col] = 0;
    			}
    	        
    	        //gioco vero e proprio
    	        boolean confermato;
    	        boolean uguali;
    	        char rigaSel, colonnaSel = ' ';
    	        char num;
    	        int errori = 0;
    	        int contaNum = 0;
    	        int riga, colonna;
    	        
    	        System.out.println("\n");
    	        
    	        uguali = true;
	        	rig = 0; while (rig < DIM && uguali) {
	        	    col = 0; while (col < DIM && uguali) {
	        	    	if (sudoku[rig][col] != sudokuIrrisolto[rig][col]) {
	        	    		uguali = false;
	        	    	}
	        	        col++;
	        	    }
	        	    rig++;
	        	}
    	        
    	        while(errori < erroriMax && !uguali) {
    	        	for (i=0; i<50; i++) System.out.print("-");
    	        	
    	        	System.out.println("\n\nErrori: "+ errori +"/"+ erroriMax +"\n");
    	        	
    	        	System.out.println(stampaSudoku(sudokuIrrisolto) +"\n");
    	        	
    	        	//numero di numeri
    	        	System.out.print("Numeri:  ");
    	        	for (i=1; i<=9; i++) System.out.printf("%2d", i);
    	        	System.out.println();
    	        	
    	        	System.out.print("Mancanti:");
    	        	for (i=1; i<=9; i++) {
    	        		contaNum = 9;
    	        		for (rig=0; rig<DIM; rig++) {
            	        	for (col=0; col<DIM; col++) {
            	        		if (sudokuIrrisolto[rig][col] == i) {
            	        			contaNum--;
            	        		}
            	        	}
            	        }
    	        		System.out.printf("%2d", contaNum);
    	        	}
    	        	
    	        	System.out.println("\n");
    	        	
    	        	do {
    	        		confermato = true;
    	        		
    	        		do {
        	        		System.out.print("Seleziona un numero: ");
        	        		num = t.next().charAt(0);
        	        		if (num < '1' || num > '9') {
    	        			    System.out.println("Valore non valido, riprova.");
    	        			}
        	        	} while (num < '1' || num > '9');
        	        	
        	        	
        	        	System.out.println("Seleziona la posizione del numero (0 per annullare l'inserimento):");
        	        	do {
        	        		System.out.print("RIGA: ");
        	        		rigaSel = t.next().charAt(0);
        	        		
        	        		if (rigaSel < '0' || rigaSel > '9') {
        	        			System.out.println("Valore non valido, riprova.");
        	        		} else if (rigaSel == '0') confermato = false;
        	        		
        	        	} while (rigaSel < 0 || rigaSel > '9');
        	        	
        	        	if (confermato) {
        	        		do {
            	        		System.out.print("COLONNA: ");
            	        		colonnaSel = t.next().charAt(0);
            	        		
            	        		if (colonnaSel < '0' || colonnaSel > '9') {
            	        			System.out.println("Valore non valido, riprova.");
            	        		} else if (colonnaSel == '0') confermato = false;
            	        		
            	        	} while (colonnaSel < '0' || colonnaSel > '9');
        	        	}
        	        	
        	        	riga = (rigaSel - '0')-1;
        	        	colonna = (colonnaSel - '0')-1;
        	        	
        	        	if (confermato) {
        	        		if (sudokuIrrisolto[riga][colonna] != 0) {
            	        		System.out.println("Casella già occupata!");
            	        		confermato = false;
            	        	}
        	        	}
        	        	
        	        	System.out.println();
    	        	} while (!confermato);
    	        	
    	        	
    	        	System.out.println();
    	        	
    	        	if (sudoku[riga][colonna] == (num - '0')) {
    	        		sudokuIrrisolto[riga][colonna] = (num - '0');
    	        		System.out.println("Giusto!");
    	        	} else {
    	        		errori++;
    	        		System.out.println("Errato!");
    	        	}
    	        	
    	        	System.out.println("Premere invio per continuare.");
    	        	t.nextLine();
    	        	t.nextLine();
    	        	
    	        	uguali = true;
    	        	
    	        	rig = 0; while (rig < DIM && uguali) {
    	        	    col = 0; while (col < DIM && uguali) {
    	        	    	if (sudoku[rig][col] != sudokuIrrisolto[rig][col]) {
    	        	    		uguali = false;
    	        	    	}
    	        	        col++;
    	        	    }
    	        	    rig++;
    	        	}
    	        	
    	        	System.out.println();
    	        	
    	        }//fine loop partita
    	        
    	        
    	        if (errori >= erroriMax) System.out.println("Hai perso.\n");
    	        if (uguali) {
    	        	System.out.println("Hai vinto!\n");
    	        	System.out.println(stampaSudoku(sudoku) +"\n");
    	        }
    	        
    	        
    	        for (i=0; i<50; i++) System.out.print("-");
    	        System.out.println("\n");
    	    
        	} //fine azione = 1
        	
    	} while (azione != '0'); //fine loop gioco
    	
    	System.out.println("Grazie per aver giocato!");
		t.close();
		
    } //fine main
    
    
    //funzione stampa
    public static String stampaSudoku(int[][] sudoku) {
        final int DIM = sudoku.length;
        int i, rig, col;
        String s = "";
        
        s += "   ";
        for (i = 0; i < DIM; i++) {
        	s += " "+ (i+1);
        	if ((i+1) % 3 == 0) s += "  ";
        }
        s += "\n";
        
        //stampa contorno superiore
        s += "  ";
        for (i = 0; i < 25; i++) {
            if (i % 8 != 0) s += "-";
            else s += "+";
        }
        s += "\n";

        //stampa interna
        for (rig = 0; rig < DIM; rig++) {
        	//stampa colonna
        	s += (rig + 1) +" ";
            s += "|";
            for (col = 0; col < DIM; col++) {
            	if (sudoku[rig][col] != 0) {
            		s += " "+ sudoku[rig][col];
            	} else {
            		s += " .";
            	}
            	
            	//separatore verticale
                if ((col + 1) % 3 == 0) s += " |";
            }
            
            
            //separatore orizzontale
            if ((rig + 1) % 3 == 0) {
                s += "\n";
                s += "  "; //spazio tra inizio schermo e i +
                i = 0;
                while (i < 25 && rig < DIM - 1) {
                    if (i % 8 != 0) s += "-";
                    else s += "+";
                    i++;
                }
            }

            if (rig < DIM - 1) s += "\n";
            
        }
        
        for (i = 0; i < 25; i++) {
            if (i % 8 != 0) s += "-";
            else s += "+";
        }

        return s;
    }

} //fine classe
