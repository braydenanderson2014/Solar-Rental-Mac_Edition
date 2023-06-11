package com.solarrentalmac.SelfUpdater;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

public class Updater {

    private static final Path UPDATE_FOLDER = Paths.get("update");
    private static final String CREDENTIALS_FILE_PATH = "/path/to/your/service/account/credentials.json";
    private static final String DRIVE_FOLDER_ID = "folderId"; // Folder id in Google Drive where source code is located
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public boolean isUpdateAvailable() throws IOException {
        // For simplicity, let's assume that we always have an update.
        // In real scenarios, you would need to implement version checking logic here.
        return true;
    }

    public void startUpdate() throws IOException, InterruptedException {
        if (!isUpdateAvailable()) {
            System.out.println("No updates available.");
            return;
        }

        System.out.println("Update available. Updating...");

        // Download source code
        try {
            downloadSourceCode();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Compile source code
        compileSourceCode();

        // Delete source code
        deleteSourceCode();
    }

    private void downloadSourceCode() throws IOException, GeneralSecurityException {
    GoogleCredentials credentials = GoogleCredentials
            .fromStream(Files.newInputStream(Paths.get(CREDENTIALS_FILE_PATH)))
            .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive.readonly"));
    Drive driveService = new Drive.Builder(
            GoogleNetHttpTransport.newTrustedTransport(),
            GsonFactory.getDefaultInstance(),
            new HttpCredentialsAdapter(credentials))
            .setApplicationName("SelfUpdater")
            .build();

    FileList result = driveService.files().list()
            .setQ("'" + DRIVE_FOLDER_ID + "' in parents")
            .execute();

    for (com.google.api.services.drive.model.File file : result.getFiles()) {
        if (file.getName().endsWith(".java")) {
            try (OutputStream outputStream = new FileOutputStream(UPDATE_FOLDER.resolve(file.getName()).toFile())) {
                driveService.files().get(file.getId()).executeMediaAndDownloadTo(outputStream);
            }
        }
    }
}


    private void compileSourceCode() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new RuntimeException(
                    "Cannot find system Java compiler. Ensure JDK is properly installed and configured.");
        }

        try (InputStream systemIn = System.in; // Pass system streams to compiler (optional)
                OutputStream systemOut = System.out;
                OutputStream systemErr = System.err) {
            Files.walk(UPDATE_FOLDER)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> {
                        int result = compiler.run(systemIn, systemOut, systemErr, path.toString());
                        if (result != 0) {
                            throw new RuntimeException("Failed to compile source code: " + path);
                        }
                    });
        }
    }

    private void deleteSourceCode() throws IOException {
        Files.walk(UPDATE_FOLDER)
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
