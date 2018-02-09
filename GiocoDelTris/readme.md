**Davide Porcu** 

**Classe 5IA**

**ITIS "C. Zuccante"**

---
# Progetto: Gioco del Tris
Il progetto consiste nella realizzazione di un'applicazione Android che consenta di giocare a tris. 

L'applicazione è composta da 4 activity:

- `MainActivity` che permette di scegliere tra le due modalità di gioco possibili (Uomo VS Uomo e Uomo VS Macchina) attraverso due bottoni 
- `NomiGiocatoriActivity` che permette l'inserimento dei nomi dei giocatori (di uno solo se contro la macchina) attraverso due EditText
- `TrisActivity` che consiste in diverse TextView per la visualizzazione delle partite vinte da ciascun giocatore, dei nomi dei giocatori e del simbolo a loro associato. Inoltre sono presenti i 9 bottoni che costituiscono la scacchiera di gioco. La classe implementa Observer per poter essere aggiornata sullo svolgimento del gioco.
- `EsitoPartitaActivity` che permette la visualizzazione dell'esito della partita (pareggio o nome vincitore) e contiene un bottone per giocare ancora

Sono presenti quattro classi utili allo svolgimento del gioco:

- `Player` che rappresenta un giocatore con il suo nome, i suoi punti, il simbolo scelto (X o O) e se è umano 
- `Mossa` che rappresenta una mossa fatta da un giocatore attraverso delle coordinate (Riga,Colonna). Viene utilizzata per informare la classe Tris che una mossa (su un bottone) è stata eseguita
- `Notifica` che rappresenta un messaggio ricevuto dall'observer della classe Tris che, a seconda della sua natura, comporta diverse azioni di aggiornamento per TrisActivity
- `Tris` che rappresenta il gioco del tris attraverso una matrice. Contiene vari metodi per l'inizio, svolgimento e riavvio del gioco. Contiene i metodi per la verifica del vincitore o del pareggio oltre che a quelli per la determinazione delle mosse della macchina.

La creazione di nuove activity viene fatto attraverso Intent espliciti e con il passaggio di valori aggiuntivi. 

Inoltre è stato modificato il manifest dell'applicazione per bloccare la rotazione dello schermo.

La macchina esegue le mosse in modi diversi a seconda della circostanza: l'algoritmo prevede che nel caso in cui sia la macchina ad iniziare essa scelga una cella casuale, nel caso generale viene ricercata una mossa che permetta alla macchina di vincere e se tale mossa non esiste allora una che ostacoli il giocatore umano. Se non esiste neanche una mossa che possa ostacolare la vittoria dell'avversario allora viene scelta una cella casuale.
