package com.devotion.ztita.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/imagenes")
public class ImgDragAndDrop {

    private static final String RUTA_IMAGENES = "src/main/resources/static/imagenes/";

    @PostMapping("/subir")
    public ResponseEntity<Map<String, Object>> subirImagenes(@RequestParam("imagenes") List<MultipartFile> imagenes) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile imagen : imagenes) {
            if (!imagen.isEmpty()) {
                try {
                    String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
                    Path rutaArchivo = Paths.get(RUTA_IMAGENES + nombreArchivo);
                    Files.copy(imagen.getInputStream(), rutaArchivo);

                    // ✅ Esta es la ruta correcta para el frontend
                    String url = "/imagenes/" + nombreArchivo;
                    urls.add(url);

                } catch (IOException e) {
                    return ResponseEntity.status(500).body(Map.of("error", "Error al guardar imagen: " + e.getMessage()));
                }
            }
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("imgUrl", urls.get(0)); // primera imagen como principal
        respuesta.put("imagenesProductos", urls);

        return ResponseEntity.ok(respuesta);
    }
}

