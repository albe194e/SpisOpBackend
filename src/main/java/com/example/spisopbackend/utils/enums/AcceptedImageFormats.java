package com.example.spisopbackend.utils.enums;

public enum AcceptedImageFormats {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private final String format;

    AcceptedImageFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
