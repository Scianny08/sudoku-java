
public class Cella {
	private int valore;
	private boolean fixed;
	
	public Cella(int valore) {
		this.valore = valore;
		fixed = true;
	}
	
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public boolean isFixed() {
		return fixed;
	}
	
	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}
	
}
