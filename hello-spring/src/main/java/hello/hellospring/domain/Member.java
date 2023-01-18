package hello.hellospring.domain;

public class Member {
    private String Name;
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
