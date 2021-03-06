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
 * OtherTest generated by hbm2java
 */
@Entity
@Table(name = "other_test"
)
public class OtherTest implements java.io.Serializable {

    private Integer otherTestId;
    private Candidate candidate;
    private CampusProgramOfStudy campusProgramOfStudy;
    private Integer score;
    private Integer choiceNo;
    private String remarks;

    public OtherTest() {
    }

    public OtherTest(Candidate candidate, CampusProgramOfStudy campusProgramOfStudy) {
        this.candidate = candidate;
        this.campusProgramOfStudy = campusProgramOfStudy;
    }

    public OtherTest(Candidate candidate, CampusProgramOfStudy campusProgramOfStudy, Integer score, Integer choiceNo, String remarks) {
        this.candidate = candidate;
        this.campusProgramOfStudy = campusProgramOfStudy;
        this.score = score;
        this.choiceNo = choiceNo;
        this.remarks = remarks;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "other_test_id", unique = true, nullable = false)
    public Integer getOtherTestId() {
        return this.otherTestId;
    }

    public void setOtherTestId(Integer otherTestId) {
        this.otherTestId = otherTestId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campus_program_of_study_id", nullable = false)
    public CampusProgramOfStudy getCampusProgramOfStudy() {
        return this.campusProgramOfStudy;
    }

    public void setCampusProgramOfStudy(CampusProgramOfStudy campusProgramOfStudy) {
        this.campusProgramOfStudy = campusProgramOfStudy;
    }

    @Column(name = "score")
    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "choice_no")
    public Integer getChoiceNo() {
        return this.choiceNo;
    }

    public void setChoiceNo(Integer choiceNo) {
        this.choiceNo = choiceNo;
    }

    @Column(name = "remarks", length = 45)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
