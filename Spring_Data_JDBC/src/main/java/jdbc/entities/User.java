package jdbc.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("email")
    private String email;
}
