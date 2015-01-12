package tomekkup.hibernateworkout.model;

import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cacheable
@Cache(region="permissions", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="PERMISSIONS")
public class Permission extends AbstractEntityWithAutoId {
    
    private PermissionType type;
    private UserAccount account;

    public Permission() {
        super();
    }

    public Permission(PermissionType type) {
        this();
        this.type = type;
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name="TYPE", nullable=false)
    public PermissionType getType() {
        return type;
    }

    public void setType(PermissionType type) {
        this.type = type;
    }
    
    @ManyToOne(cascade= CascadeType.ALL)
    public UserAccount getAccount() {
        return account;
    }
    

    public void setAccount(UserAccount account) {
        this.account = account;
    }    
}
