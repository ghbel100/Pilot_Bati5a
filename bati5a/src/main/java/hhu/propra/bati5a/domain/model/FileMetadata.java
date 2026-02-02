package hhu.propra.bati5a.domain.model;

import hhu.propra.bati5a.domain.model.person.User;
import java.time.LocalDateTime;

public class FileMetadata {
    private String fileName;
    private long size;
    private String title;
    private String description;
    private User uploader;
    private LocalDateTime uploadDate;
    private FileType type;

    public FileMetadata(String fileName, long size, String title, String description, User uploader,
            LocalDateTime uploadDate, FileType type) {
        this.fileName = fileName;
        this.size = size;
        this.title = title;
        this.description = description;
        this.uploader = uploader;
        this.uploadDate = uploadDate;
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUploader() {
        return uploader;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public FileType getType() {
        return type;
    }
}
