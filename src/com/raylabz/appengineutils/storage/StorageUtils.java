package com.raylabz.appengineutils.storage;

import com.google.cloud.storage.*;
import com.raylabz.appengineutils.firebase.FirebaseUtils;
import com.raylabz.appengineutils.servlet.ContentType;

/**
 * Provides utility functions related to storage using Google's Cloud Storage.
 */
public class StorageUtils {

    /**
     * Uploads a file on the default storage bucket.
     * @param fileName The name of the file. May contain a folder path using the / character.
     * @param contentType The content type of the file.
     * @param contents The contents of the file as an array of bytes.
     * @return Returns a Blob.
     */
    public static Blob uploadFile(final String fileName, final ContentType contentType, final byte[] contents) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(FirebaseUtils.FIREBASE_APP.getOptions().getStorageBucket(), fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType.getMime()).build();
        return storage.create(blobInfo, contents);
    }

    /**
     * Uploads a file on the default storage bucket.
     * @param fileName The name of the file. May contain a folder path using the / character.
     * @param contentType The content type of the file
     * @param contents The contents of the file as an array of bytes.
     * @return Returns a Blob.
     */
    public static Blob uploadFile(final String fileName, final String contentType, final byte[] contents) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(FirebaseUtils.FIREBASE_APP.getOptions().getStorageBucket(), fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        return storage.create(blobInfo, contents);
    }

    /**
     * Deletes a file from the default storage bucket.
     * @param filename The name of the file. May contain a folder path using the / character.
     * @return Returns a boolean. True if file was deleted, false otherwise.
     */
    public static boolean deleteFile(final String filename) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(FirebaseUtils.FIREBASE_APP.getOptions().getStorageBucket(), filename);
        return storage.delete(blobId);
    }

}
