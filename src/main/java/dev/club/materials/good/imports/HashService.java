package dev.club.materials.good.imports;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class HashService {

    public String hash(String value) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] encoded =
            digest.digest(value.getBytes(StandardCharsets.UTF_8));

        return new String(encoded);
    }
}
