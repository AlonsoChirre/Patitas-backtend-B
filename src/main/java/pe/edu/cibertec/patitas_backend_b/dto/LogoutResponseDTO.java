package pe.edu.cibertec.patitas_backend_b.dto;

import java.util.Date;

public record LogoutResponseDTO(Boolean Resultado, Date Fecha, String MensajeError) {
}
