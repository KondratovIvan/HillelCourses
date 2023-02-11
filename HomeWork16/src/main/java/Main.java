import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseConnection dbc=new DataBaseConnection();
        dbc.getConnection();
        dbc.close();

        LessonDao ld=new LessonDao();
//          ld.save(new Lesson(5,"music",50));
//        ld.delete(1);
        System.out.println(ld.getAllLessons());
        System.out.println(ld.getLessonById(5));
    }
}
