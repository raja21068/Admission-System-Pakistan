package admission.model;
// Generated Jun 30, 2014 5:25:15 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name = "department"
)
public class Department implements java.io.Serializable {

    private Integer departmentId;
    private Faculty faculty;
    private DeptType deptType;
    private String name;
    private String remarks;
    private Set<Discipline> disciplines = new HashSet<Discipline>(0);

    public Department() {
    }

    public Department(Faculty faculty, DeptType deptType) {
        this.faculty = faculty;
        this.deptType = deptType;
    }

    public Department(Faculty faculty, DeptType deptType, String name, String remarks, Set<Discipline> disciplines) {
        this.faculty = faculty;
        this.deptType = deptType;
        this.name = name;
        this.remarks = remarks;
        this.disciplines = disciplines;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "department_id", unique = true, nullable = false)
    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_type_id", nullable = false)
    public DeptType getDeptType() {
        return this.deptType;
    }

    public void setDeptType(DeptType deptType) {
        this.deptType = deptType;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "remarks", length = 45)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @OrderBy("name")
    public Set<Discipline> getDisciplines() {
        return this.disciplines;
    }

    public void setDisciplines(Set<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}