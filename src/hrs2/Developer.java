package hrs2;

import java.util.ArrayList;

public class Developer extends Employee {

    protected String teamName;
    protected ArrayList<String> programmingLanguages = new ArrayList<>();
    protected int expYear;

    public Developer() {
    }

    
    public Developer(String teamName, int expYear, String empID, String empName,
            ArrayList<String> programmingLanguages, int baseSal) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public int getExpYear() {
        return expYear;
    }

    @Override
    public String toString() {
        return super.toString() + "_" + teamName + "_" + programmingLanguages + "_"  + expYear;
    }

    @Override
    public double getSalary() {
        if (expYear >= 5) {
            return baseSal + expYear * 2000000;
        } else if (expYear >= 3) {
            return baseSal + expYear * 1000000;
        }
        return baseSal;
    }
}
