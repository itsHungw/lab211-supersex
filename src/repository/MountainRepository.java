package repository;

import model.Mountain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MountainRepository {
    private String pathFile = "MountainList.csv";

    public Map<String, Mountain> findAll() {
        Map<String, Mountain> mountainMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            br.readLine(); // Skip header line from CSV
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4); // Split into max 4 parts to handle commas in description
                if (parts.length >= 4) {
                    if(parts[3] == null || parts[3].trim().isEmpty()){
                        parts[3] = " NULL";
                    }
                    Mountain m = new Mountain(parts[0], parts[1], parts[2], parts[3]);
                    mountainMap.put(m.getMountainCode().toUpperCase(), m);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return mountainMap;
    }
}