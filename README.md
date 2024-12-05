# SudokuGame

## Introduction

Ce projet a été conçu dans le cadre du Semestre 2 du BUT Informatique à l'IUT de Fontainebleau. L'objectif était, en JAVA, de reproduire le jeu du **Sudoku**.
Le projet a été réaliser en duo avec l'aide d'**Abed Bridja**.

## Fonctionnalités

Toutes les fonctionnalités du jeu sont indiqué dans le rapport **PDF_Sudoku-SAE21_2023.pdf**. Il présente 2 parties du jeu :
  - une partie de création de grille Sudoku
  - une partie de jeu qui récupère les grilles qui ont été créer dans la première partie du programme.

## Guide pour lancer le jeu

*Les programmes s'exécutent avec la commande `make`.*

Après avoir récupérer l'ensemble des fichiers :
  - Si vous souhaitez créer une grille, l'éditer ou en charger une existante, exécutez la commande suivante pour compiler uniquement le premier programme :
    ```bash
      make PremierProg_compil
    ```
    Puis exécutez cette commande pour le lancer :
    ```bash
      make PremierProg
    ```
    Les grilles qui ont été créer se trouveront dans votre explorateur de fichier sous le format .gri à l'emplacement que vous aurez décider.
  - Si vous souahitez jouer au Sudoku et que vous possédez déjà un fichier .gri qui représente une grille :
    ```bash
      make DeuxiemeProg_compil
      make DeuxiemeProg
    ```

## Auteurs

Abed Bridja et Christopher Dubreuil
