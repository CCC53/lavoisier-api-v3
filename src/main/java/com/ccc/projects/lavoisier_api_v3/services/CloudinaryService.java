package com.ccc.projects.lavoisier_api_v3.services;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccc.projects.lavoisier_api_v3.models.HistorialClinico;
import com.ccc.projects.lavoisier_api_v3.repositories.HistorialClinicoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final HistorialClinicoRepository historialClinicoRepository;

    @SuppressWarnings("unchecked")
    private String uploadFile(MultipartFile file, UUID historialId) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        }

        String name = historialId.toString().concat("-").concat(Long.toString(new Date().getTime()));
        
        Map<String, Object> uploadOptions = ObjectUtils.asMap(
            "resource_type", "auto",
            "public_id", name,
            "folder", "lavoisier-app"
        );
        
        if (!fileExtension.isEmpty()) {
            uploadOptions.put("format", fileExtension);
        }
        
        uploadOptions.put("flags", "attachment");
        
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
        return (String) uploadResult.get("secure_url");
    }

    @SuppressWarnings("unchecked")
    private void removeFile(String url) throws IOException {
        String[] parts = url.split("/");
        StringBuilder publicId = new StringBuilder();
        for (int i = 7; i < parts.length; i++) {
            publicId.append(parts[i]);
            if (i < parts.length - 1) publicId.append("/");
        }
        int dotIndex = publicId.lastIndexOf(".");
        if (dotIndex != -1) {
            publicId = new StringBuilder(publicId.substring(0, dotIndex));
        }
        Map<String, Object> result = cloudinary.uploader().destroy(publicId.toString(), ObjectUtils.emptyMap());
        String status = (String) result.get("result");
        if (!"ok".equals(status) && !"not found".equals(status)) {
            throw new IOException("Failed to delete file from Cloudinary: " + status);
        }
    }

    public HistorialClinico updateAlimentation(MultipartFile file, UUID id) throws IOException {
        HistorialClinico histOptional = historialClinicoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Historial not found"));
        String url = this.uploadFile(file, id);
        if (histOptional.getAlimentacion() != null) {
            removeFile(histOptional.getAlimentacion());
        }
        histOptional.setAlimentacion(url);
        return historialClinicoRepository.save(histOptional);
    }
}
