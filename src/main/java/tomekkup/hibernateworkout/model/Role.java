package tomekkup.hibernateworkout.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="ROLES", uniqueConstraints={@UniqueConstraint(columnNames = {"TYPE", "PRIORITY"})})
public class Role extends AbstractEntity {
    
    private RoleType type;
    private Byte priority;
    private Date validThru;

    public Role() {
        super();
    }

    public Role(RoleType type, Byte priority, Date validThru) {
        this();
        this.type = type;
        this.priority = priority;
        this.validThru = validThru;
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name="TYPE", nullable=false)
    public RoleType getType() {
        return type;
    }

    @Column(name="PRIORITY", nullable=false)
    public Byte getPriority() {
        return priority;
    }

    @Column(name="VALIDTHRU", nullable=true)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getValidThru() {
        return validThru;
    }

    public void setValidThru(Date validThru) {
        this.validThru = validThru;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
