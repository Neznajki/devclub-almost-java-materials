package dev.club.materials.good.imports;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileService {

    public void writeLog(String text) throws IOException {

        FileWriter writer = new FileWriter("log.txt", true);

        writer.write(LocalDateTime.now() + " -> " + text + "\n");

        writer.close();
    }

    public void writeOutput(String text) throws IOException {

        FileWriter writer = new FileWriter("output.txt", true);

        writer.write(text + "\n");

        writer.close();
    }
}
