# PROJECT_JAVA
projet_java_semestre_2

#-------STUDENT-------- 
---

- Nom: MBONING JOKUNG
- Prenom: Andy Karl


#-----------EXERCICE ATTEINT------------#
---
1-) CLIENTS
---
1-a) Besoins fonctionnels
---
- création d'un client
- editer un client
- supprimer un client
- consulter les details d'un client 
- consulter les reservations d'un client
- consulter les voitures qui ont pu être reserver par le client
- consulter le nombre de voitures reservé par un client
- consulter le nombre de reservation d'un client
- afficher la liste des clients dans le système
- rechercher un client

1-b) Besoins non fonctionnels (contraintes)
---
- un client n'ayant pas 18ans ne peut pas être créé
- un client ayant une adresse mail déjà prise ne peut pas être créé ( Nb: ne renvoie pas de message d'erreur sur la page création , mais l'actulise )
- le nom et le prénom d'un client doivent faire au moins 3 caractères
- si un client est supprimé, alors il faut supprimer les 
réservations associées

2-) VOITURES
---
2-a)Besoins fonctionnels 
---
- création d'une voiture
- editer une voiture
- supprimer une voiture
- consulter le nombre de reservation d'une voiture
- consulter le nombre de voiture dans le système
- afficher la liste des voitures dans le système
- rechecher une voiture

2-b) Besoins non fontionnels (contraintes)
---
- si un véhicule est supprimé, alors il faut supprimer les réservations associées
- une voiture doit avoir un modèle et un constructeur, son nombre de place doitêtre compris entre 2 et 9

3-) RESERVATIONS
---

3-a)Besoins fonctionnels
---
- création d'une réservation
- editer une reservation
- supprimer une reservation
- consulter le nombre de reservation 
- afficher la liste des reservations dans le système
- rechercher une reservations

3-b) Besoins non fonctionnels (contraintes)
---
- une voiture ne peux pas être réservé 2 fois le même jour
- une voiture ne peux pas être réservé 2 fois le même jour

4-) Test effectué
---
- Test sur le nombre de place doitêtre compris entre 2 et 9 (UserTest)
- Test sur un client n'ayant pas plus de 18ans ne peut pas être créé (VehicleTest)
- Test sur le servive ClientService

#-----------PROBLEME RENCONTRE------------#
---
Resoudre les contraintes :

- une voiture ne peux pas être réservé plus de 7 jours de suite par le même 
utilisateur
- une voiture ne peux pas être réservé 30 jours de suite sans pause
- Affichez un message d'erreur lorsque l'utilisateur entre des informations incorrect
- faire les tests mocks, Tests End to End, Tests de non régression sur le projet 
