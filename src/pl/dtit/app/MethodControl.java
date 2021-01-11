package pl.dtit.app;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pl.dtit.io.PrintInfo;
import pl.dtit.io.ReadDataFromFile;
import pl.dtit.io.ReadDataFromUser;
import pl.dtit.model.Baby;

import java.io.File;
import java.util.List;

public class MethodControl {
    PrintInfo printInfo;
    ReadDataFromUser readDataFromUser = new ReadDataFromUser(printInfo);
    //    String checkBy = readDataFromUser.checkByYearOrDecade();
//    int yearFromUser = readDataFromUser.getYearFromUser();
    String checkBy;
    int yearFromUser;
    int nextYearFromUser;

    //    private String fileName = getFileNameFromUser();
    private String fileName;
    private String nextFileName;

    public String getFileNameFromUser(String checkBy, int yearFromUser) {
//        String checkBy = readDataFromUser.checkByYearOrDecade();
//        int yearFromUser = readDataFromUser.getYearFromUser();
        String fileName = "";
        if (checkBy.equalsIgnoreCase("decade")) {
            fileName = "data/us_babynames_by_" + checkBy + "/yob" +
                    String.valueOf(yearFromUser) + "s.csv";
        } else if (checkBy.equalsIgnoreCase("year")) {
            fileName = "data/us_babynames_by_" + checkBy + "/yob" +
                    String.valueOf(yearFromUser) + ".csv";
        } else if (checkBy.equalsIgnoreCase("test")) {
            fileName = "data/us_babynames_" + checkBy + "/yob" +
                    String.valueOf(yearFromUser) + "short.csv";
        }
        return fileName;
    }

    private String getNextFileNameFromUser(String checkBy, int yearFromUser) {
//        String checkBy = readDataFromUser.checkByYearOrDecade();
        String fileName = "";
        if (checkBy.equalsIgnoreCase("decade")) {
            fileName = "data/us_babynames_by_" + checkBy + "/yob" +
                    String.valueOf(nextYearFromUser) + "s.csv";
        } else if (checkBy.equalsIgnoreCase("year")) {
            fileName = "data/us_babynames_by_" + checkBy + "/yob" +
                    String.valueOf(nextYearFromUser) + ".csv";
        } else if (checkBy.equalsIgnoreCase("test")) {
            fileName = "data/us_babynames_" + checkBy + "/yob" +
                    String.valueOf(nextYearFromUser) + "short.csv";
        }
        return fileName;
    }

    public String getFileName() {
        return fileName;
    }

//    public int getYearFromUser() {
//        return yearFromUser;
//    }

    public int getNextYearFromUser() {
        System.out.println("Get info about year of another file");
        return nextYearFromUser = readDataFromUser.getYearFromUser();
    }

    public ReadDataFromFile readDataFromFile = new ReadDataFromFile();
    //    public List<Baby> babies = readDataFromFile.uploadBabiesFromFile(fileName);
//    public List<Baby> babies;

    public List<Baby> getAllBabiesFromFile() {
        checkBy = readDataFromUser.checkByYearOrDecade();
        yearFromUser = readDataFromUser.getYearFromUser();
        fileName = getFileNameFromUser(checkBy, yearFromUser);
        List<Baby> babies = readDataFromFile.uploadBabiesFromFile(fileName);
        return babies;
    }

    public int getNoOfAllGirlNamesFromFile(List<Baby> babies) {
        int noOfGirlsNames = 0;
        for (Baby baby : babies) {
            if (baby.getGender().equalsIgnoreCase("F")) {
                noOfGirlsNames++;
            }
        }
        return noOfGirlsNames;
    }

    public int getNoOfAllBoyNamesFromFile(List<Baby> babies) {
        int noOfBoysNames = 0;
        for (Baby baby : babies) {
            if (baby.getGender().equalsIgnoreCase("M")) {
                noOfBoysNames++;
            }
        }
        return noOfBoysNames;
    }

    public int getNoOfAllNamesFromFile(List<Baby> babies) {
        int noOfBabiesNames = getNoOfAllGirlNamesFromFile(babies) + getNoOfAllBoyNamesFromFile(babies);
        return noOfBabiesNames;
    }

    public int getNofAllGirlsFromFile(List<Baby> babies) {
        int noOfGirls = 0;
        for (Baby baby : babies) {
            if (baby.getGender().equalsIgnoreCase("F")) {
                noOfGirls += baby.getNoOfBabesWithName();
            }
        }
        return noOfGirls;
    }

    public int getNoOfAllBoysFromFile(List<Baby> babies) {
        int noOfBoys = 0;
        for (Baby baby : babies) {
            if (baby.getGender().equalsIgnoreCase("M")) {
                noOfBoys += baby.getNoOfBabesWithName();
            }
        }
        return noOfBoys;
    }

    public int getNoOfAllBabiesFromFile(List<Baby> babies) {
        int noOfBabies = getNofAllGirlsFromFile(babies) + getNoOfAllBoysFromFile(babies);
        return noOfBabies;
    }

    public int getRank(int year, String name, String gender) {
        checkBy = readDataFromUser.checkByYearOrDecade();
        yearFromUser = readDataFromUser.getYearFromUser();
        fileName = getFileNameFromUser(checkBy, yearFromUser);
        List<Baby> babies = readDataFromFile.uploadBabiesFromFile(fileName);
        int rank = 1;

        int boysNames = 0;
        for (Baby baby : babies) {
            String nameRow = baby.getName();
            String genderRow = baby.getGender();

            if (genderRow.equalsIgnoreCase("M")) {
                boysNames = countNames(name, boysNames);
                rank = boysNames;
            }
            if (nameRow.equalsIgnoreCase(name) && genderRow.equalsIgnoreCase(gender)) {
                return rank;
            }
            rank++;
        }
        return -1;
    }

    public int countNames(String name, int count) {
        String temp = null;
        String temp1 = null;
        if (temp == null) {
            temp = name;
        }
        if (temp1 != temp) {
            temp1 = temp;
            count++;
        }
        return count;
    }

    public String getName(int year, int rank, String gender) {
        checkBy = readDataFromUser.checkByYearOrDecade();
        yearFromUser = readDataFromUser.getYearFromUser();
        fileName = getFileNameFromUser(checkBy, yearFromUser);
        List<Baby> babies = readDataFromFile.uploadBabiesFromFile(fileName);
        int noOfAllGirlNamesFromFile = getNoOfAllGirlNamesFromFile(babies);
        String name = "";

        if ((rank + noOfAllGirlNamesFromFile) > babies.size()) {
            name = "NO NAME";
        } else {
            for (Baby baby : babies) {

                if (baby.getGender().equalsIgnoreCase("F") && baby.getGender().equalsIgnoreCase(gender)) {
                    name = babies.get(rank - 1).getName();
                }
                if (baby.getGender().equalsIgnoreCase("M") && baby.getGender().equalsIgnoreCase(gender)) {
                    name = babies.get(rank - 1 + noOfAllGirlNamesFromFile).getName();
                }
            }
        }
        return name;
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);

        nextYearFromUser = newYear;
        nextFileName = getNextFileNameFromUser(checkBy, nextYearFromUser);
        List<Baby> babiesNew = readDataFromFile.uploadBabiesFromFile(nextFileName);
        int noOfAllGirlNamesFromFile = getNoOfAllGirlNamesFromFile(babiesNew);

        String nameInNewYear = "";
        if ((rank + noOfAllGirlNamesFromFile) > babiesNew.size()) {
            name = "NO NAME";
        } else {
            for (Baby baby : babiesNew) {

                if (baby.getGender().equalsIgnoreCase("F") && baby.getGender().equalsIgnoreCase(gender)) {
                    nameInNewYear = babiesNew.get(rank - 1).getName();
                }
                if (baby.getGender().equalsIgnoreCase("M") && baby.getGender().equalsIgnoreCase(gender)) {
                    nameInNewYear = babiesNew.get(rank - 1 + noOfAllGirlNamesFromFile).getName();
                }
            }
        }


        return nameInNewYear;
    }

    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int tempYear = 0;
        int previousRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String filename = f.getName();
            String getYear = filename.substring(filename.indexOf("yob") + 3, filename.indexOf(".csv"));
            tempYear = Integer.parseInt(getYear);
            int currentRank = getRank(tempYear, name, gender);
            if ((currentRank != -1) && (previousRank == 0 || currentRank < previousRank)) {
                previousRank = currentRank;
                year = tempYear;
            }
        }
        return year;
    }

    public double getAverageRank(String name, String gender) {
        // Initialize a DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Define rankTotal, howMany
        double rankTotal = 0;
        int howMany = 0;
        // For every file the directory add name rank to agvRank
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);
                if (currName.equals(name) && currGender.equals(gender)) {
                    long currRank = record.getRecordNumber();
                    rankTotal += (double) currRank;
                    howMany += 1;
                }
            }
        }
        // Define avgRank = rankTotal / howMany
        double avgRank = rankTotal / (double) howMany;
        return avgRank;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        checkBy = readDataFromUser.checkByYearOrDecade();
        yearFromUser = readDataFromUser.getYearFromUser();
        fileName = getFileNameFromUser(checkBy, yearFromUser);
        List<Baby> babies = readDataFromFile.uploadBabiesFromFile(fileName);

        int rank = getRankOfName(name, gender, babies);
        int totalNumberOfBirthsRankedHigher = 0;

        for (Baby baby : babies) {
            int babyRank = getRankOfName(baby.getName(), baby.getGender(), babies);
            if (baby.getGender().equalsIgnoreCase(gender)) {
                if (babyRank < rank) {
                    totalNumberOfBirthsRankedHigher += baby.getNoOfBabesWithName();
                }
            }
        }
        return totalNumberOfBirthsRankedHigher;
    }

    private int getRankOfName(String name, String gender, List<Baby> babies) {
        int rank = 1;

        int boysNames = 0;
        for (Baby baby : babies) {
            String nameRow = baby.getName();
            String genderRow = baby.getGender();

            if (genderRow.equalsIgnoreCase("M")) {
                boysNames = countNames(name, boysNames);
                rank = boysNames;
            }
            if (nameRow.equalsIgnoreCase(name) && genderRow.equalsIgnoreCase(gender)) {
                return rank;
            }
            rank++;
        }
        return rank;
    }

}
