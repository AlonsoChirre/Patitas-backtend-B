package pe.edu.cibertec.patitas_backend_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backend_b.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backend_b.dto.LogoutResponseDTO;
import pe.edu.cibertec.patitas_backend_b.service.AutenticacionService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Thread.sleep(Duration.ofSeconds(5));//tiempo en que va a devolver la respuesta
            String[] datosUsuario = autenticacionService.validarUsuario(loginRequestDTO);
            System.out.println("Respuesta backend : " + Arrays.toString(datosUsuario));

            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "","","");

            } else {
                return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1],datosUsuario[2],datosUsuario[3]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LoginResponseDTO("99", "Error: Ocurrio un problema", "", "","","");


        }

    }

    @PostMapping("/logout")
    public LogoutResponseDTO logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {
        try {
            Thread.sleep(Duration.ofSeconds(5));//tiempo en que va a devolver la respuesta
            Date fechaLogout = autenticacionService.CerrarSesion(logoutRequestDTO);
            System.out.println("Respuesta backend : " + fechaLogout);

            if (fechaLogout == null) {
                return new LogoutResponseDTO(false, null, "Error: No se pudo registrar auditoria");
            }
            return new LogoutResponseDTO(true,fechaLogout,"Sesion Cerrada Con Exito");


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LogoutResponseDTO(false, null, "Error: Ocurrio un problema");
        }
    }

}
