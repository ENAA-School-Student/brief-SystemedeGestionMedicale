
ALTER TABLE dossier_medical DROP FOREIGN KEY fk_patient_dossier;
ALTER TABLE rendez_vous DROP FOREIGN KEY fk_patient_rdv;
ALTER TABLE rendez_vous DROP FOREIGN KEY fk_medecin_rdv;


ALTER TABLE user ADD COLUMN nom VARCHAR(255);


ALTER TABLE medecin DROP COLUMN nom;
ALTER TABLE medecin DROP COLUMN email;
ALTER TABLE medecin MODIFY id BIGINT;


ALTER TABLE patient DROP COLUMN nom;
ALTER TABLE patient DROP COLUMN email;
ALTER TABLE patient MODIFY id BIGINT;


ALTER TABLE medecin ADD CONSTRAINT fk_medecin_user FOREIGN KEY (id) REFERENCES user(id);
ALTER TABLE patient ADD CONSTRAINT fk_patient_user FOREIGN KEY (id) REFERENCES user(id);


ALTER TABLE dossier_medical ADD CONSTRAINT fk_patient_dossier FOREIGN KEY (patient_id) REFERENCES patient(id);
ALTER TABLE rendez_vous ADD CONSTRAINT fk_patient_rdv FOREIGN KEY (patient_id) REFERENCES patient(id);
ALTER TABLE rendez_vous ADD CONSTRAINT fk_medecin_rdv FOREIGN KEY (medecin_id) REFERENCES medecin(id);
