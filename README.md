# progetto_info3_progettazione_algoritmi_20_21
Progetto inerente il corso di Informatica 3 - Modulo di Progettazione e Algoritmi per l'anno accademico 2020/2021.
Laurea Magistrale in Ingegneria Informatica - UniBG

----------------------------------------------------------------------------------------------------------------------------------------
Indicazioni dei passi di sviluppo e contenuto del progetto.
----------------------------------------------------------------------------------------------------------------------------------------
0. Formare un team di sviluppo e concordare un'idea di progetto. 
Stabilire le seguenti cose:
i. Idea e raccolta iniziale dei primi requisiti software.
ii. Stabilire una "tool chain" per lo sviluppo agile del proprio progetto, ovvero decidere quali tool utilizzare per le varie attivit� di design, sviluppo e analisi statica/dinamica del codice. 
ii. Stabilire un'agenda e un'organizzazione del team: organizzare il team e un'agenda indicativa per cadenzare le sessioni/riunioni di lavoro in base alle iterazioni e fasi del processo AMDD e alle best practice agili che si intendono adottare/sperimentare. L'agenda e la suddivisione del gruppo pu� evolvere nel tempo in base alle esigenze e competenze di ciascuno e/o in base alla decomposizione funzionale del sistema. Eleggere un leader (emerger� quasi subito da solo!). 
iii. Studiare e prendere come riferimento il "progetto guida" COCOME per modellare in UML e implementare (in Java) il codice corrispondente ovvero codice che "riflette" il design UML.

1. Seguire il processo di sviluppo agile AMDD:
(N.B.: Creare una sottocartella (e progetto) diversa per ogni iterazione (release). Le iterazioni vanno numerate!)
- Iterazione 0 (envisioning): analisi dei requisiti, modellazione dei casi d'uso (requisiti funzionali) iniziali e della configurazione iniziale dell'architettura del sistema a runtime ("topologia" del sistema: free style diagram o UML deployment diagram/component dyagram)
- Ad ogni iterazione successiva: i. Planning: brain storming con scelta e raffinamento dei casi d'uso da realizzare come incremento software da produrre nell'iterazione corrente; ii. design: modellare/raffinare in modo agile producendo un "modello di analisi" (opzionale) e un "modello di design" dell'architettura SW con componenti e design pattern da realizzare/raffinare per implementare i casi d'uso scelti e mitigare eventuali problemi emersi dall'analisi statica/dinamica; iii. Implementazione, analisi statica e dinamica (testing di unit�) del codice.
 
2. Documentazione da presentare: 
(N.B. Da produrre alla fine di ciascuna iterazione -- documentazione agile!)
i. Documentare 3 iterazioni: l'iterazione 0, un'iterazione in mezzo e quella finale. 
La documentazione di un'iterazione deve contenere:
- Documento analisi requisiti (essenzialmente documento raccolta dei requisiti funzionali e non funzionali, user story/casi d'uso UML e descrizione dei passi per i requisiti funzionali)
- Documento di design dell'architettura SW: i. vista strutturale: UML deployment/component diagram e class diagram per i dettagli delle componenti, dei tipi di dato e delle interfacce (le API!); ii. vista comportamentale: eventuali diagrammi di stato e di sequence/activity 
- Documento "design in piccolo" degli algoritmi usati (pseudocodice + analisi complessit� tempo)
- Documento analisi dinamica: casi di test e risultati di copertura con Junit e Eclemma
- Documento di analisi statica: report STAN4J 

ii. Realizzare anche una succinta guida con screenshot sull'installazione ed uso dell'applicazione

3. Rilasciare documentazione, codice sorgente, codice eseguibile (archivio Jar, script, e vari artifatti SW) su un repository (Dropbox, Github, ecc..) da condividere con il docente al momento della consegna.

Per altri dettagli sulla valutazione del progetto, vedi la presentazione "introduzione.pdf" della prima lezione del corso.  




