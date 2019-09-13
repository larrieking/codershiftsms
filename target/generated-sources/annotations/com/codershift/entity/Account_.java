package com.codershift.entity;

import com.codershift.entity.Role;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-09-13T07:17:56")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, BigInteger> phoneNumber;
    public static volatile SingularAttribute<Account, Role> role;
    public static volatile SingularAttribute<Account, String> companyName;
    public static volatile SingularAttribute<Account, String> email;

}