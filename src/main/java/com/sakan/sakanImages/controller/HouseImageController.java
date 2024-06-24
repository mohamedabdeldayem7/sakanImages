package com.sakan.sakanImages.controller;

import com.sakan.sakanImages.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/house-image")
@RequiredArgsConstructor
public class HouseImageController {

    private final FileService fileService;

    @Value("${project.house}")
    private String path;

    @GetMapping("/{filename}")
    public void serveFile(
            @PathVariable() String filename,
            HttpServletResponse response
    ) throws IOException {
        InputStream resourceFile = fileService.getResourceFile(path, filename);
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}
