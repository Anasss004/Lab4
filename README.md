# Lab 4 - Stratégies d'héritage JPA / Hibernate

Mise en œuvre des trois stratégies de mappage de l'héritage avec JPA et Hibernate (H2 Database).

## Stratégies implémentées

- **SINGLE_TABLE** (`com.example.model.singletable`) : Une seule table pour toute la hiérarchie (`tb_vehicules_single`).
- **JOINED** (`com.example.model.joined`) : Une table par classe avec clés étrangères.
- **TABLE_PER_CLASS** (`com.example.model.tableperclass`) : Une table complète par classe concrète.

## Compilation et exécution

```bash
mvn clean compile exec:java -Dexec.mainClass=com.example.App
```

<img width="1917" height="1021" alt="1" src="https://github.com/user-attachments/assets/0fd14131-a7f5-4563-9603-91ff624353b6" />
