**Davide Porcu** 

**Classe 5IA**

**ITIS "C. Zuccante"**

---
# Progetto: Flat Dice
Il progetto consiste nella realizzazione di un'applicazione Android che permetta di simulare il lancio di un dado.

L'applicazione è costituita da una activity principale che conterrà il fragment di benvenuto (WelcomeFragment) o il fragment rappresentante la faccia del dado. Il primo fragment, mostrato dopo l'apertura dell'applicazione, comunica all'utente che è possibile lanciare il dado facendo degli swipe verticali o orizzontali e che è possibile chiudere l'applicazione in qualsiasi momento eseguendo un tap con 5 dita contemporaneamente.

Per le animazioni verticali e orizzontali del primo fragment (quello di benvenuto) vengono utilizzati gli ObjectAnimator definiti in xml, mentre per le animazioni del dado sono state implementate due classi (CubeAnimation e ViewPropertyAnimation presenti in questo repository 
https://github.com/kakajika/FragmentAnimations 
)
 permettendo di ottenere un'animazione tridimensionale per la transizione da una faccia ad un'altra.

Le due classi Fragment permettono l'utilizzo di un metodo factory che consente di ottenere una istanza della classe stessa.


