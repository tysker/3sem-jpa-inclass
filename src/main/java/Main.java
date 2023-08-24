

public class Main {
    public static void main(String[] args) {
    StudentDAO dao = new StudentDAO();
//    Student updatedStudent = new Student("John", "Doe", "dootest@mail.com", 25);
//    updatedStudent.setId(1);
//    dao.updateStudent(updatedStudent);

        double avgAge = dao.getAvgAge();
        double avgOfAllStudentsWithLastNameHansen = dao.getAvgOfAllStudentsWithLastNameHansen();
        double numberOfStudentsWithLastNameJensen = dao.numberOfStudentsWithLastNameJensen();
        int oldestStudent = dao.getOldestStudent();
        int youngestStudent = dao.getYoungestStudent();
        long theSumOfAllStudentsAge = dao.getTheSumOfAllStudentsAge();

        System.out.println("avgAge: " + Math.floor(avgAge));
        System.out.println("avgOfAllStudentsWithLastNameHansen: " + avgOfAllStudentsWithLastNameHansen);
        System.out.println("numberOfStudentsWithLastNameJensen: " + numberOfStudentsWithLastNameJensen);
        System.out.println("oldestStudent: " + oldestStudent);
        System.out.println("youngestStudent: " + youngestStudent);
        System.out.println("theSumOfAllStudentsAge: " + theSumOfAllStudentsAge);


    }
}
