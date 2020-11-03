package DAO;

import models.User;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUser implements UserDao {
    private final Sql2o sql2o;

    public Sql2oUser(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (userName, userPosition, userRole) VALUES (:userName, :userPosition, :userRole)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT * FROM users";
        try (Connection con = sql2o.open()){
          return con.createQuery(sql)
                  .throwOnMappingFailure(false)
                       .executeAndFetch(User.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id =:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
