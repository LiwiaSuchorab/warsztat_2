package pl.coderslab.entity;
import pl.coderslab.BCrypt;
import java.sql.*;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM users where id = ?";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String USERS_SELECT = "SELECT * FROM users";
    private static final String USER_SELECT = "SELECT id, username, email, password FROM users WHERE id = ?";


    public User create(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, this.hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readAll() {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement prepStmt = conn.prepareStatement(USERS_SELECT);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                System.out.println("ID użytkownika: " + id + "  Nazwa użytkownika: " + username + "  Email użytkownika: " + email + "  Hasło użytkownika: " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readOne(int idToPrintData) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement prepStmt = conn.prepareStatement(USER_SELECT);
            prepStmt.setInt(1, idToPrintData);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                System.out.println("ID użytkownika: " + id + "  Nazwa użytkownika: " + username + "  Email użytkownika: " + email + "  Hasło użytkownika: " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User read(int userId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(USER_SELECT);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
