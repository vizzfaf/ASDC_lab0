import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.lang.String;

/*4. участники соревнования
        а) фамилия
        б) имя
        в) страна
        г) категория (III, II, I, NM, IM)
        д) год рождения
        е) номер в таблице*/
public class Competitor implements Comparable {
    private String name;
    private String surname;
    private String country;
    private int yearOfBirthday;
    private Category category;
    private int numberInTable;

    ///Constructor with no args
    public Competitor() {
    }

    ///Constructor with args
    public Competitor(String name, String surname, String country, int dateOfBirthday, Category category, int numberInTable){
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.yearOfBirthday = dateOfBirthday;
        this.category = category;
        this.numberInTable = numberInTable;
    }
    public int getNumberInTable() {
        return numberInTable;
    }

    ///Constructor from string
    public Competitor(String csvLine) {
        String[] arrParam = csvLine.split(";");
        name = arrParam[0];
        surname = arrParam[1];
        country = arrParam[2];
        yearOfBirthday = Integer.parseInt(arrParam[3]);
        numberInTable = Integer.parseInt(arrParam[4]);
        category = Category.valueOf(arrParam[5]);
    }

    ///Constructor copy
    public Competitor(Competitor competitor) {
        this.name = competitor.name;
        this.surname = competitor.surname;
        this.country = competitor.country;
        this.category = competitor.category;
        this.yearOfBirthday = competitor.yearOfBirthday;
        this.numberInTable = competitor.numberInTable;
    }

    ///Copying method
    public Competitor copy() {
        return new Competitor(this);
    }

    ///Equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitor that = (Competitor) o;
        return yearOfBirthday == that.yearOfBirthday &&
                numberInTable == that.numberInTable &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                country.equals(that.country) &&
                category == that.category;
    }


    public static ArrayList<Competitor> inputFromFile(File resource) {
        ArrayList<Competitor> competitorList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resource))) {
            String st;
            while ((st = br.readLine()) != null) {
                if (st.isEmpty()) {
                } else {
                    competitorList.add(new Competitor(st));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return competitorList;
    }


    public static ArrayList<Competitor> inputFromString(String resource) {
        ArrayList<Competitor> competitorList = new ArrayList<>();
        String[] array = resource.split("/n");
        for (String str : array) {
            competitorList.add(new Competitor(str));
        }
        return competitorList;
    }

    ///Object to csv format
    public String competitorToCSV() {
        String name = this.name;
        String surname = this.surname;
        String country = this.country;
        String year = String.valueOf(this.yearOfBirthday);
        String number = String.valueOf(this.numberInTable);
        String category = String.valueOf(this.category);
        return new String(name + ";" + surname + ";" + country + ";" + year + ";" + number + ";" + category);
    }


    public static void outputToFile(ArrayList<Competitor> competitors, File resources) {
        try {
            FileWriter fileWriter = new FileWriter(resources);
            for (Competitor comp : competitors) {
                fileWriter.write(comp.competitorToCSV() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void output(ArrayList<Competitor> competitors) {
        for (Competitor comp : competitors) {
            System.out.println(comp.toString());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, country, yearOfBirthday, category, numberInTable);
    }

    @Override
    public int compareTo(Object competitor) {
        int comparNumber = ((Competitor) competitor).getNumberInTable();
        return this.numberInTable - comparNumber;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", country='" + country + '\'' +
                ", yearOfBirthday=" + yearOfBirthday +
                ", category=" + category +
                ", numberInTable=" + numberInTable +
                '}';
    }
}
