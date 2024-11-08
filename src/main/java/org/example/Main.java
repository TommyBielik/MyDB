package org.example;

import UserInterface.UserInterface;
import program.Program;
import static serializer.Deserializer.deserialize;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("target/serialized/");
        Path path = file.toPath();

        Program program = null;

        // If path is empty, create a new program, else, load program.
        if(isDirEmpty(path)) {
            program = new Program();
        } else {
            program = deserialize(program);
        }

        UserInterface ui = new UserInterface(program);
        ui.start();
    }

    // Check if directory is empty
    private static boolean isDirEmpty(final Path directory) throws IOException {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }
}