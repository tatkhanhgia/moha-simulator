package com.example.hdld.application.port;

/**
 * Application port for file storage operations.
 * Implemented by infrastructure storage layer.
 */
public interface FileStoragePort {

    /**
     * Stores file content and returns the storage path/identifier.
     */
    String store(byte[] content, String fileName);

    /**
     * Deletes a file by its storage path/identifier.
     */
    void delete(String path);
}
