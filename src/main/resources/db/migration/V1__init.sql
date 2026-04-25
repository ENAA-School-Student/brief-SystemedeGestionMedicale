CREATE TABLE patient(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telephone VARCHAR(20),
    date_naissance DATE
);
CREATE TABLE medecin(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    specialite VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telephone VARCHAR(20)
);
CREATE TABLE dossier_medical(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    diagnostic TEXT,
    observations TEXT,
    date_creation DATE,
    patient_id BIGINT
);
CREATE TABLE rendez_vous(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_rendez_vous DATETIME,
    statut ENUM('EN_ATTENTE', 'CONFIRME', 'ANNULE', 'TERMINE') DEFAULT 'EN_ATTENTE',
    patient_id BIGINT,
    medecin_id BIGINT
);

