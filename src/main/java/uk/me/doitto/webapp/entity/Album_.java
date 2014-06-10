package uk.me.doitto.webapp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.me.doitto.webapp.dao.AbstractEntity_;
import uk.me.doitto.webapp.entity.Track;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-10T10:21:09")
@StaticMetamodel(Album.class)
public class Album_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Album, String> releaseDate;
    public static volatile SingularAttribute<Album, String> label;
    public static volatile SingularAttribute<Album, String> catId;
    public static volatile SetAttribute<Album, Track> tracks;

}