import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            // Display menu options
            System.out.println("\n--- Payroll System Menu ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Display All Employees");
            System.out.println("4. Remove Employee by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Full-Time Employee
                    System.out.print("Enter Name: ");
                    String fullTimeName = scanner.next();
                    System.out.print("Enter ID: ");
                    int fullTimeId = scanner.nextInt();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();

                    FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(fullTimeName, fullTimeId, salary);
                    payrollSystem.addEmployee(fullTimeEmployee);
                    System.out.println("Full-Time Employee added successfully!");
                    break;

                case 2:
                    // Add Part-Time Employee
                    System.out.print("Enter Name: ");
                    String partTimeName = scanner.next();
                    System.out.print("Enter ID: ");
                    int partTimeId = scanner.nextInt();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = scanner.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    double hourlyRate = scanner.nextDouble();

                    PartTimeEmployee partTimeEmployee = new PartTimeEmployee(partTimeName, partTimeId, hoursWorked, hourlyRate);
                    payrollSystem.addEmployee(partTimeEmployee);
                    System.out.println("Part-Time Employee added successfully!");
                    break;

                case 3:
                    // Display All Employees
                    System.out.println("\nEmployee Details:");
                    payrollSystem.displayEmployees();
                    break;

                case 4:
                    // Remove Employee by ID
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    payrollSystem.removeEmployee(removeId);
                    System.out.println("Employee removed successfully (if existed).");
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Exiting Payroll System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}