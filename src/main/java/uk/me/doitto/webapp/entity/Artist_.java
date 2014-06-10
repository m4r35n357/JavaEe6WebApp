package uk.me.doitto.webapp.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import uk.me.doitto.webapp.dao.AbstractEntity_;
import uk.me.doitto.webapp.entity.Album;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-10T10:21:09")
@StaticMetamodel(Artist.class)
public class Artist_ extends AbstractEntity_ {

    public static volatile SetAttribute<Artist, Album> albums;

}