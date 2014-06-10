package uk.me.doitto.webapp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.me.doitto.webapp.dao.AbstractEntity_;
import uk.me.doitto.webapp.entity.Album;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-10T10:12:11")
@StaticMetamodel(Track.class)
public class Track_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Track, Integer> duration;
    public static volatile SetAttribute<Track, Album> albums;
    public static volatile SingularAttribute<Track, String> url;

}