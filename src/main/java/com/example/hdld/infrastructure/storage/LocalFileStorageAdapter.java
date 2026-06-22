package com.example.hdld.infrastructure.storage;

import com.example.hdld.application.port.FileStoragePort;
import com.example.hdld.domain.exception.DomainException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class LocalFileStorageAdapter implements FileStoragePort {

    private final Path baseDir;

    public LocalFileStorageAdapter(@Value("${storage.base-path}") String basePath) {
        this.baseDir = Paths.get(basePath, "attachments").toAbsolutePath().normalize();
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(baseDir);
        } catch (IOException e) {
            throw new DomainException("Failed to create storage directory: " + baseDir, e);
        }
    }

    @Override
    public String store(byte[] content, String fileName) {
        try {
            String safeName = sanitizeFileName(fileName);
            String uniqueName = UUID.randomUUID() + "_" + safeName;
            Path target = baseDir.resolve(uniqueName).normalize();
            if (!target.startsWith(baseDir)) {
                throw new DomainException("Invalid file path detected");
            }
            Files.write(target, content);
            return target.toString();
        } catch (IOException e) {
            throw new DomainException("Failed to store file", e);
        }
    }

    @Override
    public void delete(String filePath) {
        try {
            Path path = Paths.get(filePath).normalize();
            if (!path.startsWith(baseDir)) {
                throw new DomainException("Cannot delete file outside storage directory");
            }
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new DomainException("Failed to delete file: " + filePath, e);
        }
    }

    private String sanitizeFileName(String originalFileName) {
        return originalFileName.replaceAll("[^a-zA-Z0-9._-]", "_")
                               .replaceAll("\\.\\.", "_");
    }
}
