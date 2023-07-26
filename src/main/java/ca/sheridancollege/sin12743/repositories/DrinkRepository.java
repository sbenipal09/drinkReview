package ca.sheridancollege.sin12743.repositories;

import ca.sheridancollege.sin12743.bean.Drink;
import org.apache.catalina.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import  lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class DrinkRepository {

    protected NamedParameterJdbcTemplate jdbc;


    public ArrayList<Drink> getDrinks() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks";
        ArrayList<Drink> drinks = (ArrayList<Drink>) jdbc.query(query, parameters, new BeanPropertyRowMapper<Drink>(Drink.class));

        return drinks;

    }
    public void addDrink(Drink drink) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO easy_drinks (name, main1, amount1, main2, amount2, directions) " +
                "VALUES (:name, :main1, :amount1, :main2, :amount2, :directions)";
        parameters.addValue("id", drink.getId())
                .addValue("name", drink.getName())
                .addValue("main1", drink.getMain1())
                .addValue("amount1", drink.getAmount1())
                .addValue("main2", drink.getMain2())
                .addValue("amount2", drink.getAmount2())
                .addValue("directions", drink.getDirections());
        jdbc.update(query, parameters);
    }
    public Drink getDrinkById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks WHERE id=:id";
        parameters.addValue("id", id);
        ArrayList<Drink> drinks =
                (ArrayList<Drink>)jdbc.query(query, parameters,
                        new BeanPropertyRowMapper<Drink>(Drink.class));
        if (drinks.size()>0)
            return drinks.get(0);
        return null;
    }
    public void updateDrink(Drink drink) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "UPDATE easy_drinks SET name=:name, main1=:main1, amount1=:amount1, " +
                "main2=:main2, amount2=:amount2, directions=:directions WHERE id=:id";
        parameters.addValue("name", drink.getName());
        parameters.addValue("main1", drink.getMain1());
        parameters.addValue("main1", drink.getMain1());
        parameters.addValue("amount1", drink.getAmount1());
        parameters.addValue("main2", drink.getMain2());
        parameters.addValue("amount2", drink.getAmount2());
        parameters.addValue("directions", drink.getDirections());
        parameters.addValue("id", drink.getId());
        jdbc.update(query, parameters);
    }

    public void deleteDrinkById(int id) {
        String query = "DELETE FROM easy_drinks WHERE id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource("id", id);
        jdbc.update(query, parameters);
    }
    public ArrayList<User> getUsers() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM SEC_USER";
        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));

        return users;

    }

public List<String> getRolesById(Long userId) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    String query = "SELECT user_role.userId, sec_role.roleName "
            + "FROM user_role, sec_role WHERE "
            + "user_role.roleId=sec_role.roleId and userId=:id";
    parameters.addValue("id", userId);
    ArrayList<String> roles = new ArrayList<String>();
    List<Map<String,Object>> rows= jdbc.queryForList(query, parameters);
    for (Map<String,Object> row : rows) {
        roles.add((String)row.get("roleName"));
    }
    return roles;
}
    public User getUserByUsername(String username) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        String query = "SELECT * FROM SEC_USER where username=:woof";
        parameters.addValue("woof", username);
        ArrayList<User> users = (ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));

        return users.get(0);
    }
}
