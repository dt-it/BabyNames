package pl.dtit.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.dtit.model.Baby;

public class ReadDataFromFile {
    public List<Baby> babies;

    static int line = 0;

    public List<Baby> uploadBabiesFromFile(String name) {
        babies = new ArrayList<>();
        try (
                var fileReader = new FileReader(name);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {
                if (line != -1) {
                    String[] splitColumns = nextLine.split(",");
                    babies.add(createBabies(splitColumns));
                }
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return babies;
    }

    private static Baby createBabies(String[] table) {
        String name = table[0];
        String gender = table[1];
        int noOfBabesWithName = Integer.parseInt(table[2]);

        return new Baby(name, gender, noOfBabesWithName);

    }

}
