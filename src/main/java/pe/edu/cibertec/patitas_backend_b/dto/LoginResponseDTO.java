package pe.edu.cibertec.patitas_backend_b.dto;

public record LoginResponseDTO(String Codigo, String Mensaje,String TipoDocumento, String NumeroDocumento,
                               String NombreUsuario,String CorreoUsuario) {
}
