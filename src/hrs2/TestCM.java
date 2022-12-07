package hrs2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TestCM {

    public static void main(String[] args) {
        File f = new File("text.txt");
        ;
        CompanyManagement manager = new CompanyManagement("src\\input\\ListOfEmployees.txt\\",
                "src\\input\\PLInfo.txt\\");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1: {
                    manager.printEmpList();
                    break;
                }
                case 2: {
                    System.out.print("Input Programming Language: ");
                    String pl = sc.nextLine();
                    for (Developer dev : manager.getDeveloperByProgrammingLanguage(pl)) {
                        System.out.println(dev);
                    }
                    break;
                }
                case 3: {
                    System.out.print("Input Salary: ");
                    double salary = Double.parseDouble(sc.nextLine());
                    for (Tester tester : manager.getTestersHaveSalaryGreaterThan(salary)) {
                        System.out.println(tester);
                    }
                    break;
                }
                case 4: {
                    System.out.println(manager.getEmployeeWithHighestSalary());
                    break;
                }
                case 5: {
                    System.out.println(manager.getLeaderWithMostEmployees());
                    break;
                }
                case 6: {
                    manager.printEmpList(manager.sorted());
                    break;
                }
                case 7: {
                    if (manager.writeFile("src\\hrs2\\Req2.txt\\", manager.getDeveloperByProgrammingLanguage("C++"))) {
                        System.out.println("Req2.txt written successfully");
                    } else {
                        System.out.println("Req2.txt writting failed");
                    }

                    ArrayList<Employee> empList = new ArrayList<>();
                    for (Employee e : manager.getEmployeeFromFile("src\\input\\ListOfEmployees.txt\\",
                            "src\\input\\PLInfo.txt\\")) {
                        if (e.getSalary() > 4700000) {
                            empList.add(e);
                        }
                    }

                    if (manager.writeFile("src\\hrs2\\Req3.txt\\", empList)) {
                        System.out.println("Req3.txt written successfully");
                    } else {
                        System.out.println("Req3.txt writting failed");
                    }
                    break;
                }
            }
        } while (choice >= 1 && choice < 8);
    }

    static void showMenu() {
        System.out.println("1. Read all Employees and print to screen");
        System.out.println("2. Show staff proficient in  a Programming Language");
        System.out.println("3. Show Tester has a height salary");
        System.out.println("4. Show Employee's highest salary");
        System.out.println("5. Show Leader of the Team has most Employee");
        System.out.println("6. Sort Employees as descending salary");
        System.out.println("7. Write file");
        System.out.println("8. Exit");
        System.out.print("Your options from 1 - 8: ");
    }
}
