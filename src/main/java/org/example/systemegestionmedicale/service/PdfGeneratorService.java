package org.example.systemegestionmedicale.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.example.systemegestionmedicale.DTO.DossierMedicalDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generateDossierMedicalPdf(DossierMedicalDTO dossier) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();


            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Dossier Medical", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));


            Font fontContent = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph("ID Dossier: " + dossier.getId(), fontContent));
            document.add(new Paragraph("Patient ID: " + dossier.getPatientId(), fontContent));
            document.add(new Paragraph("Diagnostic: " + dossier.getDiagnostic(), fontContent));
            document.add(new Paragraph("Observations: " + dossier.getObservations(), fontContent));
            document.add(new Paragraph("Date de création: " + dossier.getDateCreation(), fontContent));

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
