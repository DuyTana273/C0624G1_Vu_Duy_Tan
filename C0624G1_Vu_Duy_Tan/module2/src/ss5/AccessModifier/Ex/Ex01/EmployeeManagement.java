package ss5.AccessModifier.Ex.Ex01;

public class EmployeeManagement {
    public static void main(String[] args) {
        Employee employee1 = null;
        System.out.println(employee1);

        Employee employee2 = new Employee();
        System.out.println(employee2);

        Employee employee3 = new Employee("Tý", true);
        System.out.println(employee3);

        Employee employee4 = new Employee("Tèo");
        System.out.println(employee4);
    }
}
