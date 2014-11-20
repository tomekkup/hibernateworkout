/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="ACCOUNT_DETAILS")
public class AccountDetails {
    
    private Integer id;
    private UserAccount userAccount;
    private String fullName;

    @Id
    @Column(name="ACC_DET_ID")
    @GenericGenerator(name = "generator", strategy="foreign",parameters=@Parameter(name="property",value="id"))
    @GeneratedValue(generator="generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(fetch= FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Column(name="FULLNAME",nullable=false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
}