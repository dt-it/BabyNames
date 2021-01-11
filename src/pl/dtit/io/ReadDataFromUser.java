package pl.dtit.io;

import java.util.Scanner;

public class ReadDataFromUser {
    Scanner scanner = new Scanner(System.in);
    PrintInfo printInfo;

    public ReadDataFromUser(PrintInfo printInfo) {
        this.printInfo = printInfo;
    }

    public int getYearFromUser() {
        System.out.println("Get the year in which you want to check the statistics: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        return year;
    }

    public String getNameFromUser() {
        System.out.println("Get the name you want to check in the statistics: ");
        String name = scanner.nextLine();
        return name;
    }

    public String getGenderFromUser() {
        System.out.println("Get the gender of baby you want to check in the statistics: ");
        String gender = scanner.nextLine();
        return gender.toUpperCase();
    }

    public int getRankFromUser() {
        System.out.println("Get the rank number you want to check: ");
        int rank = scanner.nextInt();
        scanner.nextLine();
        return rank;
    }

    public String checkByYearOrDecade() {
        System.out.println("Would you like check the statistics by the year or decede? ");
        System.out.println("By the decade - enter 1");
        System.out.println("By the year - enter 2");
        int numberCheckBy = scanner.nextInt();
        scanner.nextLine();
        String checkBy = "";
        if (numberCheckBy == 1) {
            checkBy = "decade";
        }
        if (numberCheckBy == 2) {
            checkBy = "year";
        }
        if (numberCheckBy == 0) {
            checkBy = "test";
        }
        return checkBy;
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public void close() {
        scanner.close();
    }
}
