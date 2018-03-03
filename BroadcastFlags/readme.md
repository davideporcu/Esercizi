**Davide Porcu** 
**Classe 5IA**
**ITIS "C. Zuccante"**

---
# BroadcastReceiver and various Flags for PendingIntent
Questo progetto Android è ispirato al progetto `AM003_Receiver` di Andrea Morettin ( https://github.com/divino-marchese/android ).

Il progetto consiste nella realizzazione di un applicazione che consenta di scegliere un flag della classe PendingIntent e osservarne il diverso risultato. Il flag da utilizzare viene scelto attraverso l'utilizzo di radio button. Viene inviato un broadcast dopo un certo tempo (in secondi) indicato dal'utente (utilizzo dell'AlarmManager). 

I flag della classe PendingIntent:

- `FLAG_UPDATE_CURRENT` se il PendingIntent esiste già, allora viene aggiornato con i valori extra del pending più recente
- `FLAG_ONE_SHOT` il PendingIntent può essere utilizzato solo una volta
- `FLAG_NO_CREATE` se il PendingIntent non esiste ancora, allora non viene creato
- `FLAG_IMMUTABLE` indica che il PendingIntent non può essere modificato
- `FLAG_CANCEL_CURRENT` se il PendingIntent esiste già, allora quello corrente viene cancellato e poi ne viene creato uno uguale











