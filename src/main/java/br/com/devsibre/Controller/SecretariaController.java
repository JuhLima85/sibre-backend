package br.com.devsibre.Controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/secretaria")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://sibre-frontend-production.up.railway.app",
        "https://sibre-adm.netlify.app"
})
public class SecretariaController {

    @GetMapping("/pdf/chamada")
public ResponseEntity<Resource> baixarChamada() throws IOException {
    ClassPathResource pdf = new ClassPathResource("static/relatorios/lista_presenca.pdf");

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=chamada.pdf")
        .body(pdf);
}

@GetMapping("/pdf/qrcode")
public ResponseEntity<Resource> baixarQrCode() throws IOException {
    ClassPathResource pdf = new ClassPathResource("static/relatorios/qrcode_pix.pdf");

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=qrcode.pdf")
        .body(pdf);
}   

@GetMapping("/pdf/placa")
public ResponseEntity<Resource> baixarPlacaPreferencial() throws IOException {
    ClassPathResource pdf = new ClassPathResource("static/relatorios/placa_preferencial.pdf");

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=placa.pdf")
        .body(pdf);
}

@GetMapping("/pdf/regimento")
public ResponseEntity<Resource> baixarRegimentoInterno() throws IOException {
    ClassPathResource pdf = new ClassPathResource("static/relatorios/regimento_interno.pdf");

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=regimento.pdf")
        .body(pdf);
} 

@GetMapping("/pdf/certificado")
public ResponseEntity<Resource> baixarCertificadoMembro() throws IOException {
    ClassPathResource pdf = new ClassPathResource("static/relatorios/certificado_membro.pdf");

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificado.pdf")
        .body(pdf);
}
}
