# Battleship-with-Java (PvP) - v1.0 🛳️💥

![Java](https://img.shields.io/badge/Java-17+-orange)
![OOP](https://img.shields.io/badge/Paradigm-OOP-blue)
![Status](https://img.shields.io/badge/Project-Educational-success)
![Mode](https://img.shields.io/badge/Mode-PvP%20(Console)-brightgreen)

---

## 📖 Overview
**Battleship** è un gioco **console** sviluppato in **Java** ispirato al classico *Battaglia Navale*.

Due giocatori **posizionano le navi** sul proprio campo (senza sbirciare 🤫), poi si alternano a **sparare** finché uno dei due **affonda tutte le navi avversarie**.

Il progetto è stato realizzato per allenare:
- gestione input e validazione
- logica di gioco a turni
- separazione tra **stato** (Board) e **regole** (Logic)
- controllo delle condizioni di vittoria

---

## 👤 Author
**Vincenzo Cavallaro**  
*Studente di Ingegneria Informatica*

---

## 🎯 Objectives
- Implementare un gioco a turni completo in Java
- Gestire correttamente input e casi di errore
- Applicare i concetti base di OOP (incapsulamento e responsabilità)
- Creare una logica di “fog of war” (nebbia di guerra)
- Rilevare correttamente:
  - colpo a segno (`X`)
  - colpo in acqua (`M`)
  - nave affondata
  - vittoria finale

---

## 🕹️ Gameplay Rules (Console)
### 🧩 Placement
Ogni giocatore piazza queste navi:

| Ship | Size |
|------|------|
| Aircraft Carrier | 5 |
| Battleship | 4 |
| Submarine | 3 |
| Cruiser | 3 |
| Destroyer | 2 |

📌 Regole:
- Solo **orizzontale** o **verticale**
- Lunghezza **esatta**
- Vietata **adiacenza** (anche diagonale) tra navi
- Coordinate nel formato: `A1` ... `J10`

---

## 🌫️ Fog of War
Durante la fase di sparo, il campo dell’avversario viene mostrato con nebbia:
- le navi (`O`) sono **nascoste** come `~`
- i colpi `X` e `M` restano visibili

Layout schermo:
- **Campo avversario (fog)** in alto  
- **Campo personale (uncovered)** in basso  
- separati da:
yaml
Copia codice

---

## 🎯 Shooting
Il giocatore inserisce una coordinata (es. `B7`).

- Se colpisce una nave: `O → X`  
  Messaggio: `You hit a ship!`
- Se colpisce acqua: `~ → M`  
  Messaggio: `You missed!`
- Quando una nave viene affondata:  
  Messaggio: `You sank a ship!`
- Quando affondi l’ultima nave:  
  ✅ `You sank the last ship. You won. Congratulations!`

🔁 Nota: se spari su una cella già colpita, il gioco **ripete** hit/miss coerentemente.

---

## 🔄 Turn System (No Peeking)
Dopo ogni turno:
Press Enter and pass the move to another player

yaml
Copia codice
e lo schermo viene “pulito” (con print di righe vuote) per evitare cheat.

---

## 🧱 Project Structure

```text
battleship/
├── Main.java
│   ├── Gestisce il flow: setup player 1/2 + turni PvP
│   └── Stampa campi e messaggi di gioco
│
├── Board.java
│   ├── Mantiene lo stato del campo (grid)
│   ├── Piazzamento navi + vincolo adiacenza
│   ├── Fog-of-war rendering
│   └── Shot logic: HIT / MISS / SUNK / LAST_SUNK
│
├── Logic.java
│   ├── Parsing coordinate (A1..J10)
│   ├── Validazione limiti e allineamento
│   └── Calcolo lunghezza nave
│
└── Ship.java
    └── Modello semplice: nome + lunghezza
---

## 🧠 Concepts Applied
- Object-Oriented Programming (OOP)
- Encapsulation
- Separation of concerns
- Input validation (robusta, no crash)
- State management (board + ships status)
- Turn-based game loop

---

## ▶️ How to Run

### Compile
```bash
javac battleship/*.java
Run
bash
Copia codice
java battleship.Main
✅ Notes
Questo progetto è pensato come esercizio didattico, ma include elementi “reali” tipici dei giochi:

gestione stato e condizioni di fine partita

blocchi logici chiari (setup vs battle)

output coerente per test automatici

🚀 Possible Extensions
AI player (single-player)

Salvataggio/Caricamento partita

UI grafica (JavaFX / Swing)

Statistiche (accuracy, hits/misses per player)

Modalità “best of 3”

Copia codice
