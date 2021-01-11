package pl.dtit.model;

public class Baby {
    private String name;
    private String gender;
    private int noOfBabesWithName;

    public Baby(String name, String gender, int noOfBabesWithName) {
        this.name = name;
        this.gender = gender;
        this.noOfBabesWithName = noOfBabesWithName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getNoOfBabesWithName() {
        return noOfBabesWithName;
    }

    public void setNoOfBabesWithName(int noOfBabesWithName) {
        this.noOfBabesWithName = noOfBabesWithName;
    }

    @Override
    public String toString() {
        return name + " (" + gender + ") " + noOfBabesWithName;
    }


}
