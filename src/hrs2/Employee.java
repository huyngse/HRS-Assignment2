package hrs2;

public abstract class Employee {
    protected String empID;
    protected String empName;
    protected int baseSal;

    public Employee() {
    }

    
    public Employee(String empID, String empName, int baseSal) {
        this.empID = empID;
        this.empName = empName;
        this.baseSal = baseSal;
    }
    
    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public int getBaseSal() {
        return baseSal;
    }
    
    public abstract double getSalary();

    @Override
    public String toString() {
        return empID + "_"  + empName + "_" + baseSal;
    }
    
}
