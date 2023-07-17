package ca.sheridancollege.sin12743.bean;
import lombok.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@NoArgsConstructor
@AllArgsConstructor
@Data//@Getter  @Setter @ToString @EqualsAndHashCode
public class Drink {

    private int id;
    private String name;
    private String main1;
    private double amount1;
    private String main2;
    private double amount2;
    private String directions;


    public Drink(int id, String name) {
        this.id = id;
        this.name = name;
    }



}
