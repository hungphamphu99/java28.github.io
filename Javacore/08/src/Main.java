import entities.BizStudent;
import entities.IctStudent;
import service.BizStudentService;
import service.IctStudentService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IctStudentService ictService = new IctStudentService();
        BizStudentService bizService = new BizStudentService();

        ictService.inputIctStudents();
        bizService.inputBizStudents();

        ArrayList<IctStudent> ictStudents = ictService.getIctStudents();
        ArrayList<BizStudent> bizStudents = bizService.getBizStudents();




        for (IctStudent student : ictStudents) {
            System.out.println("Name " + student.getName() + " - " + "Overall"+ student.getPoint() + " - " + student.getMajor());
        }

        for (BizStudent student : bizStudents) {
            System.out.println("Name " +student.getName() + " - " + "Overall"+ student.getPoint() + " - " + student.getMajor());
        }
    }
}
