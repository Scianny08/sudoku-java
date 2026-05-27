# 🎮 Sudoku - Java

Un'implementazione del classico gioco **Sudoku** sviluppata in Java, con interfaccia CUI (Command User Interface).

---

## 📋 Descrizione

Il progetto consiste in un'applicazione a riga di comando che permette all'utente di giocare a Sudoku. Il programma gestisce la generazione automatica della griglia, il controllo della validità delle mosse, un sistema di errori con limite, un timer di gioco e la verifica della soluzione finale.

---

## 🚀 Funzionalità

- Generazione automatica di griglie Sudoku 9x9 valide
- 4 livelli di difficoltà predefiniti + modalità personalizzata
- Sistema di errori con limite configurabile
- Timer di gioco per misurare il tempo impiegato
- Feedback immediato dopo ogni mossa (Giusto/Errato)
- Possibilità di annullare un inserimento prima di confermarlo
- Indicatore dei numeri ancora mancanti nella griglia

---

## 🎯 Livelli di difficoltà

| Livello       | Celle visibili |
|---------------|---------------|
| Facile        | 60%           |
| Media         | 50%           |
| Difficile     | 30%           |
| Impossibile   | 20%           |
| Personalizzata | Scelta dal giocatore (0–100%) + errori permessi (1–1000) |

---

## 📁 Struttura del progetto

```
sudoku-java/
├── src/
│   └── main/
│       └── java/
│           ├── Sudoku.java         # Classe principale (main)
│           ├── Partita.java        # Gestione della partita
│           ├── LogicaPartita.java  # Logica e generazione griglia
│           ├── Tempo.java          # Timer di gioco
│           └── ...
├── .gitignore
└── README.md
```

---

## ▶️ Come eseguire il progetto

### Prerequisiti
- Java JDK 17 o superiore installato

### Compilazione ed esecuzione

```bash
# Clona la repository
git clone https://github.com/Scianny08/sudoku-java.git

# Entra nella cartella
cd sudoku-java

# Compila il progetto
javac -d out src/main/java/*.java

# Avvia il gioco
java -cp out Sudoku
```

---

## 🕹️ Come si gioca

1. Avvia l'applicazione dal terminale
2. Dal menu iniziale scegli **Nuova partita**
3. Seleziona il livello di difficoltà desiderato
4. La griglia 9x9 viene generata con alcune celle già compilate
5. Ad ogni turno:
   - Inserisci il numero da posizionare (1–9)
   - Inserisci la riga (1–9) e la colonna (1–9)
   - Digita **0** in qualsiasi momento per annullare l'inserimento
6. Il gioco segnala immediatamente se la mossa è **Giusta** o **Errata**
7. Hai vinto quando la griglia è completata correttamente
8. Hai perso quando esaurisci gli errori a disposizione

### Regole del Sudoku
- Ogni riga deve contenere i numeri da 1 a 9 senza ripetizioni
- Ogni colonna deve contenere i numeri da 1 a 9 senza ripetizioni
- Ogni sottoquadrato 3×3 deve contenere i numeri da 1 a 9 senza ripetizioni

---

## 📄 Licenza

GPL-3.0
