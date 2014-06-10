package uk.me.doitto.webapp.dao;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-10T10:21:09")
@StaticMetamodel(AbstractEntity.class)
public abstract class AbstractEntity_ { 

    public static volatile SingularAttribute<AbstractEntity, Long> id;
    public static volatile SingularAttribute<AbstractEntity, Date> accessed;
    public static volatile SingularAttribute<AbstractEntity, Date> created;
    public static volatile SingularAttribute<AbstractEntity, String> name;
    public static volatile SingularAttribute<AbstractEntity, String> comments;
    public static volatile SingularAttribute<AbstractEntity, Integer> version;
    public static volatile SingularAttribute<AbstractEntity, Date> modified;

}