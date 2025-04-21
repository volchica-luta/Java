package org.example.lab_work4;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.ICSVParser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class PersonCSVReader {

    public List<Person> parseCSV(String csvFilePath) throws Exception {
        List<Person> people = new ArrayList<>();
        String[] line = new String[]{""};
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
            ICSVParser csvParser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            assert in != null;
            InputStreamReader isReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            CSVReader reader = new CSVReaderBuilder(isReader)
                                .withCSVParser(csvParser)
                                .build();
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            // Пропускаем заголовок
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                String gender = line[2];
                String birthDate = line[3];
                String division = line[4];
                double salary = Double.parseDouble(line[5]);
                people.add(new Person(id, name, gender, division, salary, birthDate));
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка при обработке строки: " + String.join(";", line));
            System.err.println("Причина: " + e.getMessage());
        }

        return people;
    }
}

