package hrs2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CompanyManagement {

    private ArrayList<Employee> empList = new ArrayList<>();

    public CompanyManagement(String path, String path1) {
        for (Employee e : getEmployeeFromFile(path, path1)) {
            empList.add(e);
        }
    }

    public ArrayList<Employee> getEmployeeFromFile(String path, String path1) {
        ArrayList<Employee> result = new ArrayList<>();

        try {
            File f1 = new File(path);
            File f2 = new File(path1);
            if (!f1.exists() || !f2.exists()) {
                System.out.println("Input File not found!");
                return null;
            }

            FileReader fr1 = new FileReader(f1);
            BufferedReader br1 = new BufferedReader(fr1);
            String details;
            while ((details = br1.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String order = stk.nextToken();
                String empID = stk.nextToken();
                String empName = stk.nextToken();
                if (empID.charAt(0) == 'T') {
                    double bonusRate = Double.parseDouble(stk.nextToken());
                    String type = stk.nextToken();
                    int baseSal = Integer.parseInt(stk.nextToken());
                    result.add(new Tester(bonusRate, type, empID, empName, baseSal));
                } else if (empID.charAt(0) == 'D') {
                    ArrayList<String> pl = new ArrayList<>();
                    String details2;
                    FileReader fr2 = new FileReader(f2);
                    BufferedReader br2 = new BufferedReader(fr2);
                    while ((details2 = br2.readLine()) != null) {
                        StringTokenizer stk2 = new StringTokenizer(details2, ",");
                        String order2 = stk2.nextToken();
                        if (order2.equals(order)) {
                            while (stk2.hasMoreTokens()) {
                                pl.add(stk2.nextToken());
                            }
                            break;
                        }
                    }
                    String teamName = stk.nextToken();
                    int expYear = Integer.parseInt(stk.nextToken());
                    String flag = stk.nextToken();
                    if (flag.equals("L")) {
                        double bonus_rate = Double.parseDouble(stk.nextToken());
                        int baseSal = Integer.parseInt(stk.nextToken());
                        result.add(new TeamLeader(bonus_rate, teamName, expYear, empID, empName, pl, baseSal));
                    } else {
                        int baseSal = Integer.parseInt(flag);
                        result.add(new Developer(teamName, expYear, empID, empName, pl, baseSal));
                    }
                    fr2.close();
                    br2.close();
                }
            }
            fr1.close();
            br1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<Developer> getDeveloperByProgrammingLanguage(String pl) {
        ArrayList<Developer> result = new ArrayList<>();
        for (Employee e : empList) {
            if (e instanceof Developer) {
                for (String programmingLanguage : ((Developer) e).getProgrammingLanguages()) {
                    if (pl.equals(programmingLanguage)) {
                        result.add((Developer) e);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Tester> getTestersHaveSalaryGreaterThan(double value) {
        ArrayList<Tester> result = new ArrayList<>();
        for (Employee e : empList) {
            if (e instanceof Tester) {
                System.out.println(e.getSalary());
                if (e.getSalary() > value) {
                    result.add((Tester) e);
                }
            }
        }
        return result;
    }

    public Employee getEmployeeWithHighestSalary() {
        Employee max = empList.get(0);
        for (Employee e : empList) {
            if (e.getSalary() > max.getSalary()) {
                max = e;
            }
        }
        return max;
    }

    public TeamLeader getLeaderWithMostEmployees() {
        ArrayList<TeamLeader> leaderList = new ArrayList<>();
        ArrayList<Integer> numOfEmployee = new ArrayList<>();
        ArrayList<Developer> devList = new ArrayList<>();

        for (Employee e : empList) {
            if (e instanceof Developer) {
                devList.add((Developer) e);
                if (e instanceof TeamLeader) {
                    leaderList.add((TeamLeader) e);
                    numOfEmployee.add(0);
                }
            }
        }
        for (Developer dev : devList) {
            for (int i = 0; i < leaderList.size(); i++) {
                if (dev.getTeamName().equals(leaderList.get(i).getTeamName())) {
                    numOfEmployee.set(i, numOfEmployee.get(i) + 1);
                }
            }
        }

        TeamLeader max = leaderList.get(0);
        for (int i = 1; i < leaderList.size(); i++) {
            if (numOfEmployee.get(i) > numOfEmployee.get(leaderList.indexOf(max))) {
                max = leaderList.get(i);
            }
        }
        return max;
    }

    public ArrayList<Employee> sorted() {
        ArrayList<Employee> result = new ArrayList<>();
        for (Employee e : empList) {
            result.add(e);
        }
        result.sort((Employee o1, Employee o2) -> {
            if (o1.getSalary() > o2.getSalary()) {
                return -1;
            }
            if (o1.getSalary() < o2.getSalary()) {
                return 1;
            }
            return o1.getEmpName().compareTo(o2.getEmpName());
        });

        return result;
    }

    public void printEmpList() {
        for (Employee e : empList) {
            System.out.println(e);
        }
    }

    public void printEmpList(ArrayList<Employee> list) {
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    public <E> boolean writeFile(String path, ArrayList<E> list) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Object o : list) {
                pw.println(o);
            }
            fw.close();
            pw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public <E> boolean writeFile(String path, E object) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(object);
            fw.close();
            pw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
