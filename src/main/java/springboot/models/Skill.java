package springboot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import springboot.enums.SkillType;

@Getter
@Setter
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name = "skill_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SkillType skillType;

}