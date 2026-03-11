package dev.club.materials.spaghetti;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Import {

    public void processEverything(String userId) {

        System.out.println("Start processing " + userId);

        try {

            // read configuration
            Properties props = new Properties();
            props.load(new FileReader("config.properties"));

            // random decision
            if (new Random().nextBoolean()) {

                // call remote API
                URL url = new URL("https://example.com/api/user/" + userId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader =
                    new BufferedReader(new FileReader("temp.txt"));

                String line;
                StringBuilder data = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }

                reader.close();

                // write log
                FileWriter writer = new FileWriter("log.txt", true);
                writer.write(LocalDateTime.now() + " -> " + data + "\n");
                writer.close();

                // database access
                Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/test",
                    "user",
                    "password");

                PreparedStatement ps =
                    connection.prepareStatement(
                        "select * from users where id = ?");

                ps.setString(1, userId);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    String email = rs.getString("email");

                    if (email != null) {

                        // hash something
                        MessageDigest digest =
                            MessageDigest.getInstance("SHA-256");

                        byte[] hash =
                            digest.digest(email.getBytes(StandardCharsets.UTF_8));

                        System.out.println("Hashed email: " + hash.length);
                    }
                }

                connection.close();
            }

            // random file generation
            Files.write(
                Paths.get("output-" + UUID.randomUUID() + ".txt"),
                List.of("Generated at " +
                    LocalDateTime.now()
                        .format(DateTimeFormatter.ISO_DATE_TIME))
            );

            // random thread execution
            ExecutorService executor = Executors.newFixedThreadPool(2);

            Future<String> future = executor.submit(new Callable<String>() {
                @Override
                public String call() {
                    return "Task finished";
                }
            });

            System.out.println(future.get());

            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finished processing");
    }
}