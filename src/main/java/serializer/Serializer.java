package serializer;

import program.Program;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer implements Serializable {

    public static void serialize(Program program) {
        try {
            FileOutputStream fileOut = new FileOutputStream("target/serialized/Program.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(program);

            out.close();
            fileOut.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found." );
        } catch(IOException e) {
            System.out.println("Error writing to file.");
        }
        System.out.println("Saving...");
    }
}
