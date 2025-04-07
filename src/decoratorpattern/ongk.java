package decoratorpattern;

interface Employee {
    String Name();
    void doTask();
}
class EmployeeSimple implements Employee {

    private String Name;

    public EmployeeSimple(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String Name() {
        return Name;
    }

    @Override
    public void doTask() {
        System.out.println("Simple Task");

    }
}
 abstract class EmployeeDecorator implements Employee {
    protected Employee employee;
    public EmployeeDecorator(Employee employee) {
        this.employee = employee;
    }

     @Override
     public String Name() {
         return employee.Name();
     }

     @Override
     public void doTask() {
         employee.doTask();
     }
 }
 class Maneger extends EmployeeDecorator {

     public Maneger(Employee employee) {
         super(employee);
     }

     void QuanLy () {
         System.out.println("Maneger");
     }

     @Override
     public void doTask() {
         QuanLy();
         employee.doTask();
     }


 }
public class ongk {
    public static void main(String[] args) {
        Maneger m =  new Maneger(new EmployeeSimple("Maneger"));
        m.doTask();
    }
}
