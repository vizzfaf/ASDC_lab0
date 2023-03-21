import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File fileInput = new File(".\\lab0\\src\\MOCK_DATA.csv");
        String stringInput = "Chickie;Hoggan;Madagascar;1989;47;IM/nChickie;Hoggan;Madagascar;1989;47;IM";
        File fileOutput = new File(".\\lab0\\src\\MOCK_DATA2.csv");


        ArrayList<Competitor> array = Competitor.inputFromFile(fileInput);
        ArrayList<Competitor> array2 = Competitor.inputFromString(stringInput);
        Competitor.outputToFile(array,fileOutput);
        Competitor.output(array2);

    }
}