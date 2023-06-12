package com.solarrentalmac.SelfUpdater;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Updater {

    private static final Path REPO_PATH = Paths.get("/Users/braydenanderson/Documents/GitHub/Solar-Rental-Mac_Edition");
    private static final String REPO_URL = "https://github.com/braydenanderson2014/Solar-Rental-Mac_Edition.git"; // replace with your repo URL
    private static final Path COMPILED_FOLDER = Path.of("compiled");

    public void updateFromGitHub() throws IOException, GitAPIException {
        File folder = REPO_PATH.toFile();
        if (folder.exists()) {
            try (Git git = Git.open(folder)) {
                git.pull().call();
            }
        } else {
            Git.cloneRepository()
                    .setURI(REPO_URL)
                    .setDirectory(folder)
                    .call();
        }
    }

    public void compileSourceCode() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new RuntimeException(
                    "Cannot find system Java compiler. Ensure JDK is installed and properly set up.");
        }

        List<String> sourceFiles = new ArrayList<>();
        Files.walk(REPO_PATH)
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(path -> sourceFiles.add(path.toString()));

        int result = compiler.run(null, null, null, sourceFiles.toArray(new String[0]));

        if (result != 0) {
            throw new RuntimeException("Failed to compile source code.");
        }

        // You would need to create a JAR file from these .class files
        // This step is omitted here for simplicity
    }

    public void deleteSourceCode() throws IOException {
        Files.walk(REPO_PATH)
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void compileWithMaven() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("mvn", "clean", "install");
        pb.directory(REPO_PATH.toFile()); // Set the working directory to your project directory
        Process p = pb.start(); // Start the process

        // If you want to read the output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Wait for the process to finish and check the exit value
        try {
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Maven compile failed with exit code " + exitCode);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Maven compile process was interrupted", e);
        }
    }

    public void startUpdate() throws IOException, InterruptedException {
        try {
            updateFromGitHub();
        } catch (GitAPIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        compileSourceCode();

        compileWithMaven();
        deleteSourceCode();
    }

    public boolean isUpdateAvailable() throws IOException, InterruptedException, NoHeadException, GitAPIException {
        File folder = REPO_PATH.toFile();
        if (!folder.exists()) {
            return true;
        }

        try (Git git = Git.open(folder)) {
            String currentCommit = git.log().setMaxCount(1).call().iterator().next().getName();
            git.fetch().call();
            String latestCommit = git.log().setMaxCount(1).call().iterator().next().getName();
            return !currentCommit.equals(latestCommit);
        }
    }
}
