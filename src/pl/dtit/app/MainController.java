package pl.dtit.app;

import pl.dtit.exceptions.NoSuchOptionException;
import pl.dtit.io.PrintInfo;
import pl.dtit.io.ReadDataFromUser;
import pl.dtit.model.Baby;

import java.util.InputMismatchException;
import java.util.List;

public class MainController {
    MethodController methodController = new MethodController();
    PrintInfo printInfo = new PrintInfo(methodController);
    private ReadDataFromUser dataReader = new ReadDataFromUser(printInfo);

    public void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ALL_BABIES_FROM_FILE:
                    List<Baby> babies = methodController.getAllBabiesFromFile();
                    printInfo.printAllBabiesFromFile(babies);
                    break;
                case NUMBER_OF_ALL_BABIES_NAMES_FROM_FILE:
                    babies = methodController.getAllBabiesFromFile();
                    methodController.getNoOfAllNamesFromFile(babies);
                    printInfo.printNoOfAllNamesFromFile(babies);
                    break;
                case NUMBER_OF_ALL_BABIES_FROM_FILE:
                    babies = methodController.getAllBabiesFromFile();
                    methodController.getNoOfAllBabiesFromFile(babies);
                    printInfo.printNoOfAllBabiesFromFile(babies);
                    break;
                case RANK_OF_NAME:
                    int year = dataReader.getYearFromUser();
                    String name = dataReader.getNameFromUser();
                    String gender = dataReader.getGenderFromUser();
                    printInfo.printRank(year, name, gender);

                    break;
                case GET_NAME:
                    year = dataReader.getYearFromUser();
                    int rank = dataReader.getRankFromUser();
                    gender = dataReader.getGenderFromUser();
                    printInfo.printGetName(year, rank, gender);
                    break;
                case WHAT_NAME_IN_ANOTHER_YEAR:
                    name = dataReader.getNameFromUser();
                    year = dataReader.getYearFromUser();
                    printInfo.printLine("New year in which you want to check what is name");
                    int newYear = dataReader.getYearFromUser();
                    gender = dataReader.getGenderFromUser();
                    methodController.whatIsNameInYear(name, year, newYear, gender);
                    break;
                case YEAR_OF_HIGHEST_RANK:
                    name = dataReader.getNameFromUser();
                    gender = dataReader.getGenderFromUser();
                    printInfo.printYearOfHighestRank(name, gender);
                    break;
                case AVG_RANK:
                    name = dataReader.getNameFromUser();
                    gender = dataReader.getGenderFromUser();
                    printInfo.printAverageRank(name, gender);
                    break;
                case TOTAL_BIRTH_RANKED_HIGHER:
                    year = dataReader.getYearFromUser();
                    name = dataReader.getNameFromUser();
                    gender = dataReader.getGenderFromUser();
                    printInfo.printTotalBirthsRankedHigher(year, name, gender);
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printInfo.printLine("Selected option is invalid. Please try again.");

            }
        } while (option != Option.EXIT);
    }

    private void printOptions() {
        printInfo.printLine("Choose an option: ");
        for (Option option : Option.values()) {
            printInfo.printLine(option.toString());
        }
    }

    private Option getOption() {
        boolean noError = false;
        Option option = null;
        while (!noError) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                noError = true;
            } catch (NoSuchOptionException e) {
                printInfo.printLine(e.getMessage() + ", try again.");
            } catch (InputMismatchException e) {
                printInfo.printLine("Provided input is not a number, try again.");
            }
        }
        return option;
    }

    private void exit() {
        printInfo.printLine("Closing the program ... See you!");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Exit"),
        ALL_BABIES_FROM_FILE(1, "Display all babies from chosen file"),
        NUMBER_OF_ALL_BABIES_NAMES_FROM_FILE(2, "Display number of all babies names from chosen file"),
        NUMBER_OF_ALL_BABIES_FROM_FILE(3, "Display number of all babies from chosen file"),
        RANK_OF_NAME(4, "Display rank of chosen baby name from file"),
        GET_NAME(5, "Display baby name on chosen rank in file"),
        WHAT_NAME_IN_ANOTHER_YEAR(6, "Display name would have been named if they were born in a different " +
                "year, based on the same popularity"),
        YEAR_OF_HIGHEST_RANK(7, "Display year of highest rank with chosen name"),
        AVG_RANK(8, "Display average rank with chosen name"),
        TOTAL_BIRTH_RANKED_HIGHER(9, "Display total number of births of those names who are ranked " +
                "higher than chosen name");


        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No such option as " + option);

            }

        }
    }
}
