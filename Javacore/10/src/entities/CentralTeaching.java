package entities;

public class CentralTeaching {
    private Manager manager;
    private Class_1 aClass1;

    public CentralTeaching(Manager aClass1, Class_1 students) {
        this.manager = aClass1;
        this.aClass1 = students;

    }

    public Manager getManager() {
        return manager;
    }

    public Class_1 getaClass() {
        return aClass1;
    }



    @Override
    public String toString() {
        return "CentralTeaching{" +
                "manager=" + manager +
                ", aClass=" + aClass1 +
                '}';
    }
}
