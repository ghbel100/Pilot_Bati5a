package hhu.propra.bati5a.domain.service;

import hhu.propra.bati5a.domain.model.FileMetadata;

public class UploadedFile {
    private FileMetadata metadata;
    private byte[] content;

    public UploadedFile(FileMetadata metadata, byte[] content) {
        this.metadata = metadata;
        this.content = content;
    }

    public FileMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(FileMetadata metadata) {
        this.metadata = metadata;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
