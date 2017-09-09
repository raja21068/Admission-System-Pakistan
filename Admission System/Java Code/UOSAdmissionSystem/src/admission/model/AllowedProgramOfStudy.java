package admission.model;
// Generated Jun 30, 2014 5:25:15 PM by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProgramProgramOfStudy generated by hbm2java
 */
@Entity
@Table(name = "allowed_program_of_study"
)
public class AllowedProgramOfStudy implements java.io.Serializable {

    private Integer id;
    private ProgramOfStudy programOfStudy;
    private Program program;
    private String remarks;

    public AllowedProgramOfStudy() {
    }

    public AllowedProgramOfStudy(ProgramOfStudy programOfStudy, Program program) {
        this.programOfStudy = programOfStudy;
        this.program = program;
    }

    public AllowedProgramOfStudy(ProgramOfStudy programOfStudy, Program program, String remarks) {
        this.programOfStudy = programOfStudy;
        this.program = program;
        this.remarks = remarks;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_of_study_id", nullable = false)
    public ProgramOfStudy getProgramOfStudy() {
        return this.programOfStudy;
    }

    public void setProgramOfStudy(ProgramOfStudy programOfStudy) {
        this.programOfStudy = programOfStudy;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    public Program getProgram() {
        return this.program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Column(name = "remarks", length = 45)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return this.getProgramOfStudy().toString();
    }
}
