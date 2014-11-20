/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="ROLES")
public class Role extends AbstractEntity {
    
    private UserAccount user;
    private RoleType type;
    private Byte priority;
    private Date validThru;

    @ManyToOne
    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
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
