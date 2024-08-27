import entities.TechMasterStudent;
import service.BizStudentService;
import service.IctStudentService;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TechMasterStudent> allStudents = new ArrayList<>();

        BizStudentService bizService = new BizStudentService();
        IctStudentService ictService = new IctStudentService();

        allStudents.addAll(bizService.inputBizStudents());
        allStudents.addAll(ictService.inputIctStudents());

        for (TechMasterStudent student : allStudents) {
            System.out.println("Name: " + student.getName()+" - " + "Major: " + student.getMajor()+" - "+"Overall :"+ student.getPoint());
        }
    }
}
