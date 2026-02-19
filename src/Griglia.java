//composizione: Griglia è una matrice di Celle, le Celle esistono indipendentemente da Griglia

public class Griglia {
	private Cella[][] griglia;
	private int DIM;
	
	public Griglia() {
		DIM = 9;
		griglia = new Cella[DIM][DIM];
		for (int i=0; i<DIM; i++) {
		    for (int j=0; j<DIM; j++) {
		        griglia[i][j] = new Cella(0); 
		    }
		}
	}
	
	public void setValoreCella(int val, int x, int y) {
		griglia[x][y].setValore(val);
	}
	
	public int getValoreCella(int x, int y) {
		return griglia[x][y].getValore();
	}
	
	public Cella getCella(int x, int y) {
		return griglia[x][y];
	}
	
	public int getDIM() {
		return DIM;
	}

	public void setDIM(int dIM) {
		DIM = dIM;
	}

	public boolean isEmpty() {
		for (int rig=0; rig<DIM; rig++) {
			for (int col=0; col<DIM; col++) {
				if (griglia[rig][col].getValore() != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean isFull() {
		for (int rig=0; rig<DIM; rig++) {
			for (int col=0; col<DIM; col++) {
				if (griglia[rig][col].getValore() == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String toString() {
	    int i, rig, col;
	    String s = "";
	    
	    if (!isEmpty()) {
	        // stampa intestazione colonne (1-9)
	        s += "   ";
	        for (i = 0; i < DIM; i++) {
	            s += " "+ (i+1);
	            if ((i+1) % 3 == 0) s += "  ";
	        }
	        s += "\n";

	        // stampa contorno superiore
	        s += "  ";
	        for (i = 0; i < 25; i++) {
	            if (i % 8 != 0) s += "-";
	            else s += "+";
	        }
	        s += "\n";

	        // stampa righe interne
	        for (rig = 0; rig < DIM; rig++) {
	            // stampa numero di riga e bordo sinistro
	            s += (rig + 1) +" ";
	            s += "|";

	            // stampa valori delle celle della riga
	            for (col = 0; col < DIM; col++) {
	                if (griglia[rig][col].getValore() != 0) {
	                    s += " "+ griglia[rig][col].getValore();
	                } else {
	                    // cella vuota rappresentata con punto
	                    s += " .";
	                }

	                // separatore verticale ogni 3 colonne
	                if ((col + 1) % 3 == 0) s += " |";
	            }

	            // separatore orizzontale ogni 3 righe
	            if ((rig + 1) % 3 == 0) {
	                s += "\n";
	                s += "  ";
	                i = 0;
	                while (i < 25 && rig < DIM - 1) {
	                    if (i % 8 != 0) s += "-";
	                    else s += "+";
	                    i++;
	                }
	            }

	            if (rig < DIM - 1) s += "\n";
	        }

	        // stampa contorno inferiore
	        for (i = 0; i < 25; i++) {
	            if (i % 8 != 0) s += "-";
	            else s += "+";
	        }

	        return s;
	    }
	    return null;
	}
}
