package edu.utc._2015cpsc2100.ejkk;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-16T03:47:29.663-0500")
@StaticMetamodel(Reservation.class)
public class Reservation_ {
	public static volatile SingularAttribute<Reservation, String> resID;
	public static volatile SingularAttribute<Reservation, Date> pickUpDate;
	public static volatile SingularAttribute<Reservation, Date> dropOffDate;
	public static volatile SingularAttribute<Reservation, Vehicle> vehicle;
	public static volatile SingularAttribute<Reservation, Double> price;
	public static volatile SingularAttribute<Reservation, Boolean> canceled;
}
