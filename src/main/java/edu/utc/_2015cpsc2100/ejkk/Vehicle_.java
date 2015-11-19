package edu.utc._2015cpsc2100.ejkk;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-13T21:11:00.335-0500")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ {
	public static volatile SingularAttribute<Vehicle, String> make;
	public static volatile SingularAttribute<Vehicle, String> model;
	public static volatile SingularAttribute<Vehicle, Integer> year;
	public static volatile SingularAttribute<Vehicle, String> category;
	public static volatile SingularAttribute<Vehicle, Double> rate;
	public static volatile SingularAttribute<Vehicle, String> description;
	public static volatile SingularAttribute<Vehicle, String> vin;
	public static volatile SetAttribute<Vehicle, Reservation> reservations;
}
