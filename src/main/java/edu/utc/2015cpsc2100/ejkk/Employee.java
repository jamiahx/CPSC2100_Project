/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

/**
 *
 * @author kalebsanchez
 */
public class Employee 
{
    private String reservation;
    private String vehicle;
    private String newVehicle;
    /**
     * 
     * @param aReservation the reservation that is going to be updated
     * @param aVehicle the vehicle that is going to to be updated
     * @param aNewVehicle the new vehicle that needs to be registered
     */
    public Employee(String aReservation, String aVehicle, String aNewVehicle)
    {
        reservation = aReservation;
        vehicle = aVehicle;
        newVehicle = aNewVehicle;
    }
    /**
     * 
     * @return newVehicle that needs to be registered in the system 
     */

   public String registerNewVehicle()
   {
       return newVehicle;
   }
   
   /**
    * 
    * @return vehicle that can be updated in the list already
    */
   public String updateExistingVehicle()
   {
       return vehicle;
   }
   /**
    * 
    * @return reservation that has been updated
    */
   public String updateReservation()
   {
       return reservation;
   }
}
