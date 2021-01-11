package pl.dtit.io;

import pl.dtit.app.MethodControl;
import pl.dtit.model.Baby;

import java.util.List;


public class PrintInfo {
    MethodControl methodControl;
//    ReadDataFromUser readDataFromUser = new ReadDataFromUser();

    public <E> void printLine(E e) {
        System.out.println(e);
    }

    public PrintInfo(MethodControl methodControl) {
        this.methodControl = methodControl;
    }

    public void printAllBabiesFromFile(List<Baby> babies) {
        printLine("List of all babies sorted by number of birth (first girls, than boys):");
        for (Baby baby : babies) {
            System.out.println(baby);
        }
    }

    public void printNoOfAllNamesFromFile(List<Baby> babies) {
        int noOfGirlsNames = methodControl.getNoOfAllGirlNamesFromFile(babies);
        int noOfBoysNames = methodControl.getNoOfAllBoyNamesFromFile(babies);
        int noOfBabiesNames = methodControl.getNoOfAllNamesFromFile(babies);

        printLine("Total Births - number of names: " + noOfBabiesNames);
        printLine("Total Girls - number of names: " + noOfGirlsNames);
        printLine("Total Boys - number of names: " + noOfBoysNames);
    }

    public void printNoOfAllBabiesFromFile(List<Baby> babies) {
        int noOfGirls = methodControl.getNofAllGirlsFromFile(babies);
        int noOfBoys = methodControl.getNoOfAllBoysFromFile(babies);
        int noOfBabies = methodControl.getNoOfAllBabiesFromFile(babies);
        printLine("Total Births: " + noOfBabies);
        printLine("Total Girls: " + noOfGirls);
        printLine("Total Boys: " + noOfBoys);
    }

    public void printRank(int year, String name, String gender) {
        int rank = methodControl.getRank(year, name, gender);
        if (rank == -1) {
            printLine("No babies with name " + name + " and gender " + gender);
        } else {
            printLine("Rank of the name " + name + " in the file for the given gender " +
                    "(from highest number of births): " + rank);
        }
    }

    public void printGetName(int year, int rank, String gender) {
        String name = methodControl.getName(year, rank, gender);
        printLine("Name on the rank " + rank + " (gender " + gender + "): " + name);

    }

    public void printWhatIsNameInYear(String name, int year, int newYear, String gender, String newName) {
        printLine(name + " born in year " + year + " would be " + newName + " if she was born in " + newYear);
    }

    public void printYearOfHighestRank(String name, String gender) {
        int yearOfHighestRank = methodControl.yearOfHighestRank(name, gender);
        printLine("Year of highest rank with name " + name + " and gender " + gender +
                " from selected files is " + yearOfHighestRank);
    }

    public void printAverageRank(String name, String gender) {
        double averageRank = methodControl.getAverageRank(name, gender);
        printLine("Average rank with name " + name + " and gender " + gender + " from selected files is " +
                averageRank);
    }

    public void printTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirthsRankedHigher = methodControl.getTotalBirthsRankedHigher(year, name, gender);
        printLine("Total number of births of those names with the same gender as " + gender +
                " and same year as " + year + " who are ranked higher than name " + name +
                " is " + totalBirthsRankedHigher);

    }
}
