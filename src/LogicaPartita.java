//associazione: LogicaPartita usa Griglia ma Griglia esiste indipendentemente da LogicaPartita

public class LogicaPartita {
	private Griglia griglia;
	private Griglia grigliaIrrisolta;
	private Difficolta difficolta;
	
	public LogicaPartita() {
		griglia = new Griglia();
		grigliaIrrisolta = new Griglia();
		difficolta = new Difficolta(1, 0);
	}

	public Griglia getGriglia() {
		return griglia;
	}

	public void setGriglia(Griglia griglia) {
		this.griglia = griglia;
	}
	
	public Difficolta getDifficolta() {
		return difficolta;
	}

	public Griglia getGrigliaIrrisolta() {
		return grigliaIrrisolta;
	}
	
	public Griglia generaSudoku() {
		int rig, col;
		boolean trovato;
		int tentativi;
		int nuovaCella;
		int i;
		int inizioR, inizioC;
		int x, y;
		final int DIM = griglia.getDIM();
		
		//STEP 1
        for (rig=0; rig<DIM; rig++) {
        	for (col=0; col<DIM; col++) {
        		griglia.setValoreCella(0, rig, col);
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
        				if (nuovaCella == griglia.getValoreCella(rig, i)) {
        					trovato = true;
        				}
        				i++;
        			}
        			
        			//ricerca sulle colonne
	    			i=0; while(i<rig && !trovato) {
	    				if (nuovaCella == griglia.getValoreCella(i, col)) {
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
	    			        if (nuovaCella == griglia.getValoreCella(x, y)) {
	    			            trovato = true;
	    			        }
	    			        y++;
	    			    }
	    			    x++;
	    			}
	    			tentativi++;
        		} while (trovato && tentativi <= 50);
        		
        		//STEP 6
        		if (tentativi < 100) {
        			griglia.setValoreCella(nuovaCella, rig, col);
        		} else {
        			for (i=0; i<DIM; i++) {
        				griglia.setValoreCella(0, rig, i);
        			}
        			
        			rig--; if (rig < 0) rig = 0;
    				col = -1;
        		}
        		
        	} //fine for colonne
        	
        } //fine for righe
        
        return griglia;
	}
	
	public void generaSudokuIrrisolto(int livelloDiff) {
		switch (livelloDiff) {
    	case 1:
    		difficolta.setVisibilita(0.4);
    		difficolta.setErroriMax(5);
    		break;
    	
    	case 2:
    		difficolta.setVisibilita(0.5);
    		difficolta.setErroriMax(3);
    		break;
    	
    	case 3:
    		difficolta.setVisibilita(0.7);
    		difficolta.setErroriMax(2);
    		break;
    	
    	case 4:
    		difficolta.setVisibilita(0.8);
    		difficolta.setErroriMax(1);
    		break;
		
		default:
			break;
		}
		
		irrisolutore();
	}
	
	public void generaSudokuIrrisolto(double visibilita, int erroriMax) {
		difficolta = new Difficolta(visibilita, erroriMax);
		irrisolutore();
	}
	
	private void irrisolutore() {
		int rig, col;
		int i;
		final int DIM = griglia.getDIM();
		
		for (rig=0; rig<DIM; rig++) {
        	for (col=0; col<DIM; col++) {
        		grigliaIrrisolta.setValoreCella(griglia.getValoreCella(rig, col), rig, col);
        		grigliaIrrisolta.getCella(rig, col).setFixed(true);
        	}
        }
        
        int celleCoperte = (int)(DIM*DIM*difficolta.getVisibilita());
        
        for(i=0; i<celleCoperte; i++) {
			do {
				rig = (int)(Math.random()*9);
				col = (int)(Math.random()*9);
			} while(grigliaIrrisolta.getValoreCella(rig, col) == 0);
			
			grigliaIrrisolta.setValoreCella(0, rig, col);
			grigliaIrrisolta.getCella(rig, col).setFixed(false);
		}
	}
	
	public String numMancantiToString() {
		int i, rig, col;
		final int DIM = griglia.getDIM();
		int numMancanti;
		String s = "";
		
		s += "Numeri:  ";
    	for (i=1; i<=9; i++) s += String.format("%2d", i);
    	s += "\n";
    	
    	s += "Mancanti:";
    	for (i=1; i<=9; i++) {
    		numMancanti = 9;
    		for (rig=0; rig<DIM; rig++) {
	        	for (col=0; col<DIM; col++) {
	        		if (grigliaIrrisolta.getValoreCella(rig, col) == i) {
	        			numMancanti--;
	        		}
	        	}
	        }
    		s += String.format("%2d", numMancanti);
    	}
    	
    	return s;
	}
}
