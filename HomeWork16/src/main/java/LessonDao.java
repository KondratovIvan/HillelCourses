import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {
    public int save(Lesson lesson){
        int status = 0;
        try {
            Connection connection=DataBaseConnection.getConnection();
            PreparedStatement ps=connection.prepareStatement("insert into lesson(id,name,homework_id) values (?,?,?)");
            ps.setInt(1, lesson.getId());
            ps.setString(2, lesson.getName());
            ps.setInt(3, lesson.getHomework_id());

            status = ps.executeUpdate();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }
    public  int delete(int id) {

        int status = 0;

        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from lesson where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }
    public List<Lesson> getAllLessons() {

        List<Lesson> listEmployees = new ArrayList<>();

        try {
            Connection connection=DataBaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from lesson");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Lesson lesson = new Lesson(0,null,0);

                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                lesson.setHomework_id(rs.getInt(3));


                listEmployees.add(lesson);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmployees;
    }
    public Lesson getLessonById(int id) {

        Lesson lesson = new Lesson(0,null,0);

        try {
            Connection connection=DataBaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from lesson where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lesson.setId(rs.getInt(1));
                lesson.setName(rs.getString(2));
                lesson.setHomework_id(rs.getInt(3));

            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

}
