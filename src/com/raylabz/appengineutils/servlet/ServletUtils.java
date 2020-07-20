package com.raylabz.appengineutils.servlet;

import javax.servlet.http.HttpServletResponse;

/**
 * Provides utility functions related to servlets.
 */
public class ServletUtils {

    /**
     * Enables Cross-Origin Resource Sharing for a response.
     * @param response The response.
     */
    public static void enableCORS(final HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
    }

    /**
     * Sets the content type for a response.
     * @param response The response.
     * @param contentType The content type.
     */
    public static void setContentType(final HttpServletResponse response, final ContentType contentType) {
        response.setContentType(contentType.getMime() + ";charset=UTF-8");
    }

    /**
     * Sets the content type for a response.
     * @param response The response.
     * @param contentType The content type.
     */
    public static void setContentType(final HttpServletResponse response, final String contentType) {
        response.setContentType(contentType + ";charset=UTF-8");
    }

}
