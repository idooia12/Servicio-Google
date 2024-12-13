package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/google")
@Tag(name = "Google Controller", description = "Operaciones relacionadas con la autenticación mediante Google")
public class GoogleController {

    private final AuthService authService;

    public GoogleController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
        summary = "Validar si el usuario está registrado",
        description = "Verifica si un usuario con el correo electrónico proporcionado ya está registrado.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping("/validar")
    public boolean  validarEstaRegistrado(
        @Parameter(description = "Correo electrónico del usuario", required = true) @RequestParam("email") String email
    ) {
    	try {
    		boolean succes = authService.validar(email);
    		if (succes) {
    			new ResponseEntity<>("Usuario registrado en google", HttpStatus.OK);
    			return true;
    		} else {
                new ResponseEntity<>("Usuario no existe en google service", HttpStatus.UNAUTHORIZED);
                return false;
    		}
    	}catch (Exception e) {
                new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                return false;
    	}
    }

    @Operation(
        summary = "Validar contraseña del usuario",
        description = "Verifica si la contraseña proporcionada para el correo electrónico es válida.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Contraseña válida"),
            @ApiResponse(responseCode = "401", description = "Contraseña inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping("/login")
    public boolean validarContrasena(
        @Parameter(description = "Correo electrónico del usuario", required = true) @RequestParam("email") String email,
        @Parameter(description = "Contraseña del usuario", required = true) @RequestParam("password") String password
    ) {
    	try {
    		boolean succes = authService.login(email, password);
    		if (succes) {
    			new ResponseEntity<>("Login en google exitoso", HttpStatus.OK);
    			return true;
    		} else {
                new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
                return false;
    		}
    	}catch (Exception e) {
                new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                return false;
    	}
    }
}
