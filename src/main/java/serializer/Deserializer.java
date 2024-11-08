package serializer;

import program.Program;

import java.io.*;

public class Deserializer {

    public static Program deserialize(Program program) {
        try {
            FileInputStream fileIn = new FileInputStream("target/serialized/Program.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            program = (Program) inputStream.readObject();

            fileIn.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException f) {
            System.out.println("Error opening file.");
        } catch (ClassNotFoundException g) {
            throw new RuntimeException(g);
        }
        return program;
    }
}
