package springDI_study.demo.domain;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
public class Member {
    //@Column(name = "username") //db column 명
    private String Name;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
