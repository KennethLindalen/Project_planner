package dev.kennethlindalen.projectplanner.Projects;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class ProjectDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String member_one;

    @Column
    private String member_two;

    @Column
    private String memeber_three;

    @Column
    private String member_four;

    @Column
    private String member_five;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMember_one() {
        return member_one;
    }

    public void setMember_one(String member_one) {
        this.member_one = member_one;
    }

    public String getMember_two() {
        return member_two;
    }

    public void setMember_two(String member_two) {
        this.member_two = member_two;
    }

    public String getMemeber_three() {
        return memeber_three;
    }

    public void setMemeber_three(String memeber_three) {
        this.memeber_three = memeber_three;
    }

    public String getMember_four() {
        return member_four;
    }

    public void setMember_four(String member_four) {
        this.member_four = member_four;
    }

    public String getMember_five() {
        return member_five;
    }

    public void setMember_five(String member_five) {
        this.member_five = member_five;
    }
}
