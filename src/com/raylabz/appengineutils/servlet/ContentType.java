package com.raylabz.appengineutils.servlet;

/**
 * Enumerates the possible ContentTypes in an HTTP request/response.
 */
public enum ContentType {

    JAVASCRIPT("application/javascript", "js", "mjs"),
    OGG("application/ogg", "ogg"),
    PDF("application/pdf", "pdf"),
    XML("application/xml", "xml"),
    JSON("application/json", "json"),
    ZIP("application/zip", "zip"),

    MULTIPART_FORM("x-www-form-urlencoded"),
    FORM("multipart/form-data"),

    MPEG_AUDIO("audio/mpeg", "mpeg", "mpg"),
    WMA("audio/x-ms-wma", "wma"),
    WAV("audio/x-wav", "wav"),

    GIF("image/gif", "gif"),
    JPEG("image/jpeg", "jpeg", "jpg"),
    PNG("image/png", "png"),
    TIFF("image/tiff", "tiff"),
    SVG("image/svg+xml", "svg"),

    CSS("text/css", "css"),
    CSV("text/csv", "csv"),
    HTML("text/html", "html", "htm"),
    TEXT("text/plain", "txt"),

    MPEG_VIDEO("video/mpeg", "mpeg"),
    MP4("video/mp4", "mp4"),
    WMV("video/x-ms-wmv", "wmv"),
    FLV("video/x-flv", "flv"),
    WEBM("video/webm", "webm"),

    MS_EXCEL("application/vnd.ms-excel", "xls", "xlsx"),
    MS_POWERPOINT("application/vnd.ms-powerpoint", "ppt", "pptx"),
    MS_WORD("application/msword", "doc", "docx")

    ;

    /**
     * The MIME text of the content type.
     */
    private final String mime;

    /**
     * The file extensions for this content type.
     */
    private final String[] extensions;

    /**
     * Constructs a ContentType.
     * @param mime The MIME text of the content type.
     * @param extensions The valid extensions for this type.
     */
    ContentType(String mime, String... extensions) {
        this.mime = mime;
        this.extensions = extensions;
    }

    /**
     * Retrieves the MIME text.
     * @return Returns a String.
     */
    public String getMime() {
        return mime;
    }

    /**
     * Retrieves the valid extensions for this type.
     * @return Returns an array of Strings.
     */
    public String[] getExtensions() {
        return extensions;
    }

}
