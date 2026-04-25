ALTER TABLE dossier_medical
    ADD CONSTRAINT fk_patient_dossier
        FOREIGN KEY (patient_id) REFERENCES patient(id);

ALTER TABLE dossier_medical
    ADD CONSTRAINT unique_patient_dossier UNIQUE (patient_id);

ALTER TABLE rendez_vous
    ADD CONSTRAINT fk_patient_rdv
        FOREIGN KEY (patient_id) REFERENCES patient(id);

ALTER TABLE rendez_vous
    ADD CONSTRAINT fk_medecin_rdv
        FOREIGN KEY (medecin_id) REFERENCES medecin(id);