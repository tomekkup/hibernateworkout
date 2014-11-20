/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author tkuprowski
 */
@Entity
@Table(name="USERACCOUNT")
public class UserAccount extends AbstractEntity {
    
    private String name;
    private String password;
    private List<Role> roles = new ArrayList<Role>();
    private AccountDetails details;
    
    public UserAccount() {
    }

    public UserAccount(Integer id, String name, String password) {
        setId(id);
        setName(name);
        setPassword(password);
    }

    @Column(nullable=false, unique=true, name="NAME")
    public String getName() {
        return name;
    }

    @OneToOne(cascade= CascadeType.ALL)
    public AccountDetails getDetails() {
        return details;
    }

    public void setDetails(AccountDetails details) {
        this.details = details;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable=false, name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
