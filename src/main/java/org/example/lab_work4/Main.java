package org.example.lab_work4;


import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        List<Person> persons = new PersonCSVReader().parseCSV("foreign_names.csv");
        System.out.println(persons);
    }
}
