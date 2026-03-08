
public class Tempo {
    private long inizio;
    private long inizioPausa;
    private long tempoPausa;
    private boolean inPausa;

    public Tempo() {
        reset();
    }

    public void reset() {
        inizio = System.currentTimeMillis();
        inizioPausa = 0;
        tempoPausa = 0;
        inPausa = true;
    }

    public void inizio() {
        if (inPausa) {
            inPausa = false;
            inizio = System.currentTimeMillis();
        }
    }

    public void pausa() {
        if (!inPausa) {
            inPausa = true;
            inizioPausa = System.currentTimeMillis();
        }
    }

    public void riprendi() {
        if (inPausa) {
            inPausa = false;
            long pausaAttuale = System.currentTimeMillis() - inizioPausa;
            tempoPausa = pausaAttuale + tempoPausa;
        }
    }

    public long getSecondi() {
        long millisecondi = (System.currentTimeMillis() - inizio) - tempoPausa;
        long secondi = millisecondi / 1000;
        return secondi;
    }

    public String toString() {
        long minuti = getSecondi() / 60;
        long secondi = getSecondi() % 60;
        return String.format("Tempo di gioco: " + minuti + "m " + secondi + "s");
    }

}
