# Algeo01-22126 - Mandala Jaya
## Table of Contents
- [Algeo01-22126 - Mandala Jaya](#algeo01-22126---mandala-jaya)
  - [Table of Contents](#table-of-contents)
  - [General Information](#general-information)
  - [Contributor](#contributor)
  - [Technologies Used](#technologies-used)
  - [Features](#features)
  - [Structure](#structure)
  - [How to Use](#how-to-use)
    - [Dependencies](#dependencies)
    - [Installation](#installation)
    - [Program Execution](#program-execution)

## General Information
A matrix calculator developed in Java. this repository is an archive of the files for Tugas Besar 1 IF2123 Aljabar Linear dan Geometri 2023/2024. The main goal of this project is to make a Java library that is able to solve Linear Algebra problems using several methods including matrices. These methods include Gauss Elimination, Gauss-Jordan Elimination, Inverse, and Cramer's Rule. Aside of that, this project also include several implementation of the library: Polinomial Interpolation, Bicubic Interpolation, and Double Linear Regression.

## Contributor
- 13522126 Rizqika Mulia Pratama
- 13522134 Shabrina Maharani
- 13522148 Auralea Alvinia Syaikha

## Technologies Used
- Java 

## Features
- Resolving systems of linear equations through the application of the Gauss Elimination Method, Gauss-Jordan Elimination Method, Inverse Method, and Cramer's Rule.
- Determining determinants using the Cofactor and Inverse matrix techniques.
- Calculating the inverse of a matrix utilizing both Gauss elimination and the Adjoint matrix method.
- Addressing polynomial interpolation challenges.
- Tackling bicubic interpolation issues.
- Handling double regression problems.

## Structure

```
├── README.md
├── .gitignore
├── cover.jpg
│ 
├── bin
│   ├── balikan.class
│   ├── bicubicInterpolation.class
│   ├── determinan.class
│   ├── InterpolasiPolinom.class
│   ├── Main.class
│   ├── matrix.class
│   ├── matrixOperation.class
│   ├── RegresiLinierBerganda.class
│   └── SPL.class
│       
├── doc
│   └── Algeo01-22126.pdf
│ 
├── src
│   ├── balikan.java
│   ├── bicubicInterpolation.java
│   ├── daterminan.java
│   ├── InterpolasiPolinom.java
│   ├── Main.java
│   ├── matrix.java
│   ├── matrixOperation.java
│   ├── RegresiLinierBerganda.java
│   └── SPL.java
│ 
└── test
    ├── 1_a.txt
    ├── 1_b.txt
    ├── 1_c.txt
    ├── 1_d_6.txt
    ├── 1_d_10.txt
    ├── 2_a.txt
    ├── 2_b.txt
    ├── 3_a.txt
    ├── 3_b.txt
    ├── 4.txt
    ├── 5_a_1.txt
    ├── 5_a_2.txt
    ├── 5_a_3.txt
    ├── 5_a_4.txt
    ├── 5_b_a.txt
    ├── 5_b_b.txt
    ├── 5_b_c.txt
    ├── 5_b_d.txt
    ├── 5_c_4.txt
    ├── 5_c_5.txt
    ├── 5_c_6.txt
    ├── 6.txt
    ├── 7_a.txt
    ├── 7_b.txt
    ├── 7_c.txt
    ├── 7_d.txt
    ├── solusi_1_a_balikan.txt
    ├── solusi_1_a_cramer.txt
    ├── solusi_1_a_gauss.txt
    ├── solusi_1_a_gaussjordan.txt
    ├── solusi_1_b_balikan.txt
    ├── solusi_1_b_cramer.txt
    ├── solusi_1_b_gauss.txt
    ├── solusi_1_b_gaussjordan.txt
    ├── solusi_1_c_balikan.txt
    ├── solusi_1_c_cramer.txt
    ├── solusi_1_c_gauss.txt
    ├── solusi_1_c_gaussjordan.txt
    ├── solusi_1_d_6_balikan.txt
    ├── solusi_1_d_6_cramer.txt
    ├── solusi_1_d_6_gauss.txt
    ├── solusi_1_d_6_gaussjordan.txt
    ├── solusi_1_d_10_balikan.txt
    ├── solusi_1_d_10_cramer.txt
    ├── solusi_1_d_10_gauss.txt
    ├── solusi_1_d_10_gaussjordan.txt
    ├── solusi_2_a_balikan.txt
    ├── solusi_2_a_cramer.txt
    ├── solusi_2_a_gauss.txt
    ├── solusi_2_a_gaussjordan.txt
    ├── solusi_2_b_balikan.txt
    ├── solusi_2_b_cramer.txt
    ├── solusi_2_b_gauss.txt
    ├── solusi_2_b_gaussjordan.txt
    ├── solusi_3_a_balikan.txt
    ├── solusi_3_a_cramer.txt
    ├── solusi_3_a_gauss.txt
    ├── solusi_3_a_gaussjordan.txt
    ├── solusi_3_b_balikan.txt
    ├── solusi_3_b_cramer.txt
    ├── solusi_3_b_gauss.txt
    ├── solusi_3_b_gaussjordan.txt
    ├── solusi_4_balikan.txt
    ├── solusi_4_cramer.txt
    ├── solusi_4_gauss.txt
    ├── solusi_4_gaussjordan.txt
    ├── solusi_5_a_1.txt
    ├── solusi_5_a_2.txt
    ├── solusi_5_a_3.txt
    ├── solusi_5_a_4.txt
    ├── solusi_5_b_a.txt
    ├── solusi_5_b_b.txt
    ├── solusi_5_b_c.txt
    ├── solusi_5_b_d.txt
    ├── solusi_6.txt
    ├── solusi_7_a.txt
    ├── solusi_7_b.txt
    ├── solusi_7_c.txt
    └── solusi_7_d.txt
    
```

---

## How to Use

### Dependencies
- Java Virtual Environment
- Java Development Kit

### Installation
- Download and install [Java](https://www.java.com/en/download/)
- Download and install [Java Development Kit](https://www.oracle.com/java/technologies/downloads/)
- Download all folder and files on this repository or simply clone this repo!

### Program Execution
    git clone https://github.com/rizqikapratamaa/Algeo01-22126
    cd Algeo01-22126/bin
    java Main

From Mandala Jaya, with love <3
![image](https://github.com/rizqikapratamaa/Algeo01-22126/blob/5c9a30ef98e3573cfa9463abbc5d19f58662a9eb/cover.jpg)