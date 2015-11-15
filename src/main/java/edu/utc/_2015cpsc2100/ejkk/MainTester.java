/**
 * Copyright 2015 Emma Perez, Jeremiah Gaertner, Kate Siprelle, Kaleb Sanchez
 * emma11.gene@gmail.com
 * jamiahx@gmail.com
 * kalebsanchez23@yahoo.com
 * ksiprelle@gmail.com
 * 
 * This file is a part of ORS.
 *
 * ORS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * ORS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ORS.  If not, see <http://www.gnu.org/licenses/>.
 */


package edu.utc._2015cpsc2100.ejkk;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;


/**
 * 
 * @authors Kate Siprelle, Kaleb Sanchez, Jeremiah Gaertner, Emma Perez
 * Group # 4
 *
 */
public class MainTester
{
	public static void main(String[]args) throws MandrillApiError, IOException
	{
		Scanner s = new Scanner(System.in);
		
		UserDatabase udb = new UserDatabase();
		VehicleDatabase vdb = new VehicleDatabase();
		ReservationDatabase rdb = new ReservationDatabase();
		
		udb.load();
		vdb.load();
		rdb.load();
		
		
		
		int loginAttempts = 0;
		
		String answer = "0";
		
		RegistrationForm rf = new RegistrationForm(null, null, null, null, null, null, null);
		User u = new User(rf);
		
		//while(! answer.equalsIgnoreCase("Q"))
//		while(true)
//		{	
//			if (answer.equals("0"))
//			{
//				if (! u.getRole().equalsIgnoreCase("User")) //LOGGED IN OPTIONS
//				{
//					System.out.println("(1) Basic Search");
//					System.out.println("(2) Advanced Search");
//					if (u.getRole().equalsIgnoreCase("Employee") || u.getRole().equalsIgnoreCase("Manager")) { System.out.println("(3) To access employee options"); }
//					if (u.getRole().equalsIgnoreCase("Manager")) { System.out.println("(4) To access manager options"); }
//					System.out.println("(Q) Quit");
//					
//					answer = s.next();
//					
//					if (answer.equals("1")) { answer = "3"; }
//					else if (answer.equals("2")) { answer = "4"; }
//					else if (answer.equals("3") && u.getRole().equalsIgnoreCase("Employee")) { answer = "5"; }
//					else if (answer.equals("4") && u.getRole().equalsIgnoreCase("Manager")) { answer = "6"; }
//					else if (answer.equalsIgnoreCase("Q")) { break; }
//					else
//					{
//						System.out.println("Invalid response.");
//						answer = "0";
//					}
//				}
//				else //NOT LOGGED IN OPTIONS
				
					JFrame frame1 = new JFrame();
					frame1.setSize(400, 600);
					frame1.setLocationRelativeTo(null);
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setLayout(new FlowLayout());
					
					JButton login = new JButton("Login");
					frame1.add(login);
					JButton create = new JButton("Create Account");
					frame1.add(create);
					JButton bSearch = new JButton("Basic Search");
					frame1.add(bSearch);
					JButton aSearch = new JButton("Advanced Search");
					frame1.add(aSearch);
					JButton quit = new JButton("Quit");
					frame1.add(quit);
					frame1.setVisible(true);
					//System.out.println("(1) Login");
					//System.out.println("(2) Create Account");
					//System.out.println("(3) Basic Search");
					//System.out.println("(4) Advanced Search");
					//System.out.println("(Q) Quit");
					
					//answer = s.next();
					
					//Login Process
					login.addActionListener(new ActionListener(){
				
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							frame1.setVisible(false);
							JFrame loginFrame = new JFrame();
							loginFrame.setSize(400, 600);
							loginFrame.setLocationRelativeTo(null);
							loginFrame.setLayout(new FlowLayout());
							
							final int TEXT_FIELD_SIZE = 20;
							final JTextField username = new JTextField(TEXT_FIELD_SIZE);
							loginFrame.add(username);
							username.setText("Enter username here");
							String strUsername = username.getText();
							
							final JTextField password = new JTextField(TEXT_FIELD_SIZE);
							loginFrame.add(password);
							password.setText("Enter password here");
							String strPassword = password.getText();
							
							loginFrame.setVisible(true);
							
							if (loginAttempts > 0)
								{
									JOptionPane.showMessageDialog( null, "Login attemps remaining" + ( 3 - loginAttempts), "Warning", JOptionPane.ERROR_MESSAGE);
								}
								
							if (User.login(strUsername, strPassword) != null)
								{
									u = User.login(strUsername, strPassword);
									if (u.getRole().equalsIgnoreCase("Customer"))
									{
										u = (Customer) u;
										//answer = "0";
									}
									if (u.getRole().equalsIgnoreCase("Employee"))
									{
										u = (Employee) u;
										//answer = "5";
									}
									if (u.getRole().equalsIgnoreCase("Manager"))
									{
										u = (Manager) u;
										//answer = "6";
									}
									else if (loginAttempts < 2)
										{
											loginAttempts++;
											while (true)
											{
												JOptionPane.showMessageDialog( null, "Username or password is incorrect", "Warning", JOptionPane.ERROR_MESSAGE);
												JButton tryAgain = new JButton("Try again");
												JButton createNew = new JButton("Create new account");
												loginFrame.add(tryAgain);
												loginFrame.add(createNew);
												//System.out.println("Username or password is incorrect.");
												//System.out.println("(1) Login again");
												//System.out.println("(2) Create new account");
												
												//answer = s.next();
												
												if (answer.equals("1") || answer.equals("2")) { break; }
												else { System.out.println("Invalid response."); }
											}
										}
										else
										{
											while (true)
											{
												System.out.println("Login terminated.");
												System.out.println("(1) Create a new account");
												System.out.println("(2) Go back");
												
							
							
						}
					}
							
}
					
					
	}
					});
					create.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							JFrame createAcc = new JFrame();
							frame1.setVisible(false);
							
							createAcc.setSize(400, 600);
							createAcc.setLocationRelativeTo(null);
							createAcc.setLayout(new FlowLayout());
							
							
							final int TEXT_FIELD_SIZE = 20;
							final JTextField firstName = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(firstName);
							firstName.setText("Enter first name");
							String strFirstName = firstName.getText();
							
							final JTextField lastName = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(lastName);
							lastName.setText("Enter last name");
							String strLastName = lastName.getText();
							
							final JTextField address = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(address);
							address.setText("Enter your address");
							String strAddress = address.getText();
							
							final JTextField email = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(email);
							email.setText("Enter your email address");
							String strEmail = email.getText();
							
							final JTextField phone = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(phone);
							phone.setText("Enter your phone number");
							String strPhone = phone.getText();
							
							final JTextField username = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(username);
							username.setText("Enter a preferred username");
							String strUsername = username.getText();
							
							final JTextField password = new JTextField(TEXT_FIELD_SIZE);
							createAcc.add(password);
							password.setText("Enter a password");
							String strPassword = password.getText();
							
							JButton cancle = new JButton("Cancle");
							createAcc.add(cancle);
							
							cancle.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									
									frame1.setVisible(true);
									createAcc.setVisible(false);
								}
								
							});
							
							
							JButton submit = new JButton("Submit");
							createAcc.add(submit);
							
							
						submit.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
							if (User.checkUsernameAvailability(strUsername))
							{
									RegistrationForm newForm = new RegistrationForm(strFirstName, strLastName, strAddress, strEmail, strPhone, strUsername, strPassword);
									User.createAccount("Customer", newForm);
									JOptionPane.showMessageDialog( null, "Your account has been created", ":)", JOptionPane.INFORMATION_MESSAGE);
									//System.out.println("Your account has been created.");
									//answer = "0";
							}
							else
							{
								boolean firstTime = true;
									
									JOptionPane.showMessageDialog( null, "The username that your chose is already taken. Enter a different username",
											"Warning", JOptionPane.ERROR_MESSAGE);
									//System.out.println("The username that you chose is already taken. Enter a different username or");
									
									//need to make a cancel button
									//System.out.println("(1) To cancel");
									
									//How do I make it read another username?
									//username = s.next();
									
									
									if (!firstTime)
									{
											System.out.println("The username that you chose is already taken. Enter a different username or");
											System.out.println("(1) To cancel.");
											
											//username = s.nextLine();
									}
									if (! username.equals("(1)"))
									{
										if (User.checkUsernameAvailability(strUsername))
										{
												RegistrationForm newForm = new RegistrationForm(strFirstName, strLastName, strAddress, strEmail, strPhone, strUsername, strPassword);
												User.createAccount("Customer", newForm);
												JOptionPane.showMessageDialog( null, "Your account has been created", ":)", JOptionPane.INFORMATION_MESSAGE);
												
										}
									}
										
							}
								}
							
				
						});
						
						createAcc.setVisible(true);
							
					}
					
		
	
});
					}
}



			
//			if (answer.equals("1")) //LOGIN PROCESS
//			{
//				
//				if (loginAttempts > 0)
//				{
//					System.out.println("Login attempts remaining: " + (3 - loginAttempts));
//				}
//				System.out.println("Enter your username.");
//				String username = s.next();
//				System.out.println("Enter your password.");
//				String password = s.next();
//				
//				if (User.login(username, password) != null)
//				{
//					u = User.login(username, password);
//					if (u.getRole().equalsIgnoreCase("Customer"))
//					{
//						u = (Customer) u;
//						answer = "0";
//					}
//					if (u.getRole().equalsIgnoreCase("Employee"))
//					{
//						u = (Employee) u;
//						answer = "5";
//					}
//					if (u.getRole().equalsIgnoreCase("Manager"))
//					{
//						u = (Manager) u;
//						answer = "6";
//					}
//					System.out.println("Login successful.");
//				}
//				else if (loginAttempts < 2)
//				{
//					loginAttempts++;
//					while (true)
//					{
//						System.out.println("Username or password is incorrect.");
//						System.out.println("(1) Login again");
//						System.out.println("(2) Create new account");
//						
//						answer = s.next();
//						
//						if (answer.equals("1") || answer.equals("2")) { break; }
//						else { System.out.println("Invalid response."); }
//					}
//				}
//				else
//				{
//					while (true)
//					{
//						System.out.println("Login terminated.");
//						System.out.println("(1) Create a new account");
//						System.out.println("(2) Go back");
//						
//						String option = s.next();
//						
//						if (option.equals("1"))
//						{
//							answer = "2";
//							break;
//						}
//						else if (option.equals("2"))
//						{
//							answer = "0";
//							break;
//						}
//						else { System.out.println("Invalid response."); }
//					}
//				}
//			}
//			else if (answer.equals("2")) //ACCOUNT CREATION PROCESS--USER
//			{
//				System.out.println("Enter your first name.");
//				String firstName = s.next();
//				System.out.println("Enter your last name.");
//				String lastName = s.next();
//				System.out.println("Enter your address.");
//				String address = s.next();
//				System.out.println("Enter your email address.");
//				String emailAddress = s.next();
//				System.out.println("Enter your phone number.");
//				String phoneNumber = s.next();
//				System.out.println("Choose a username.");
//				String username = s.next();
//				System.out.println("Choose a password.");
//				String password = s.next();
//				
//				if (User.checkUsernameAvailability(username))
//				{
//					RegistrationForm newForm = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
//					User.createAccount("Customer", newForm);
//					System.out.println("Your account has been created.");
//					answer = "0";
//				}
//				else
//				{
//					boolean firstTime = true;
//					
//					System.out.println("The username that you chose is already taken. Enter a different username or");
//					System.out.println("(1) To cancel");
//					
//					username = s.next();
//					
//					while(! username.equals("1"))
//					{
//						if (!firstTime)
//						{
//							System.out.println("The username that you chose is already taken. Enter a different username or");
//							System.out.println("(1) To cancel.");
//							
//							username = s.nextLine();
//						}
//						if (! username.equals("(1)"))
//						{
//							if (User.checkUsernameAvailability(username))
//							{
//								RegistrationForm newForm = new RegistrationForm(firstName, lastName, address, emailAddress, phoneNumber, username, password);
//								User.createAccount("Customer", newForm);
//								System.out.println("Your account has been created.");
//							}
//						}
//						else { answer = "0"; }
//						firstTime = false;
//					}
//					if (username.equals("1")) { answer = "0"; }
//					
//				}
//			}
//			else if (answer.equals("3")) //BASIC SEARCH PROCESS
//			{
//				answer = u.basicSearch();
//				
//			}
//			else if (answer.equals("4")) //ADVANCED SEARCH PROCESS
//			{
//				answer = u.advancedSearch();
//			}
//			else if (answer.equals("5") && (u.getRole().equalsIgnoreCase("Employee") || u.getRole().equalsIgnoreCase("Manager"))) //EMPLOYEE POST-LOGIN OPTIONS
//			{
//				System.out.println("(1) To register a vehicle");
//				System.out.println("(2) To search and update a vehicle");
//				System.out.println("(3) To search for a user");
//				System.out.println("(4) To search and update a reservation");
//				System.out.println("(5) To access customer options");
//				if (u.getRole().equalsIgnoreCase("Manager")) { System.out.println("(6) To access manager options"); }
//				System.out.println("(Q) Quit");
//				
//				String answer1 = s.next();
//				
//				if (answer1.equals("1")) { ((Employee) u).registerVehicle(); }
//				else if (answer1.equals("2")) { ((Employee) u).searchVehicles(); }
//				else if (answer1.equals("3")) { ((Employee) u).searchUsers(); }
//				else if (answer1.equals("4"))
//				{
//					Reservation selectedReservation = ((Employee) u).searchReservations();
//					while (selectedReservation != null)
//					{
//						System.out.println("(1) To update this reservation");
//						System.out.println("(2) To go back to employee options");
//						
//						String answer2 = s.next();
//						
//						if (answer2.equals("1")) { ((Employee) u).updateReservation(selectedReservation); }
//						else if (answer2.equals("2")) { break; }
//						else { System.out.println("Invalid response."); }
//					}
//				}
//				else if (answer1.equals("5")) { answer = "0"; }
//				else if (answer1.equals("6") && u.getRole().equalsIgnoreCase("Manager")) { answer = "6"; }
//				else if (answer1.equalsIgnoreCase("Q")) { break; }
//				else { System.out.println("Invalid response."); }
//			}
//			else if (answer.equals("6") && u.getRole().equalsIgnoreCase("Manager")) //MANAGER POST-LOGIN OPTIONS
//			{
//				System.out.println("(1) To create an account");
//				System.out.println("(2) To update or delete an existing account");
//				System.out.println("(3) To update or cancel a reservation");
//				System.out.println("(4) To access employee options");
//				System.out.println("(5) To access customer options");
//				System.out.println("(Q) Quit");
//				
//				String answer1 = s.next();
//				
//				if (answer1.equals("1"))
//				{
//					((Manager) u).createAccount();
//				}
//				else if (answer1.equals("2"))
//				{
//					User selectedUser = ((Employee) u).searchUsers();
//					while (selectedUser != null)
//					{
//						System.out.println("(1) To update this account");
//						System.out.println("(2) To delete this account");
//						System.out.println("(3) To go back to manager options");
//						
//						String answer2 = s.next();
//						
//						if (answer2.equals("1"))
//						{
//							((Manager) u).updateAccount(selectedUser);
//							break;
//						}
//						else if (answer2.equals("2"))
//						{
//							((Manager) u).deleteAccount(selectedUser);
//							break;
//						}
//						else if (answer2.equals("3")) { break; }
//						else { System.out.println("Invalid response."); }
//					}
//				}
//				else if (answer1.equals("3"))
//				{
//					Reservation selectedReservation = ((Employee) u).searchReservations();
//					while (selectedReservation != null)
//					{
//						System.out.println("(1) To update this reservation");
//						System.out.println("(2) To cancel this reservation");
//						System.out.println("(3) To go back to manager options");
//						
//						String answer2 = s.next();
//						
//						if (answer2.equals("1"))
//						{
//							((Employee) u).updateReservation(selectedReservation);
//							break;
//						}
//						else if (answer2.equals("2"))
//						{
//							((Manager) u).cancelReservation(selectedReservation);
//							break;
//						}
//						else if (answer2.equals("3")) { break; }
//						else { System.out.println("Invalid response."); }
//					}
//				}
//				else if (answer1.equals("4"))
//				{
//					answer = "5";
//				}
//				else if (answer1.equals("5"))
//				{
//					answer = "0";
//				}
//				else if (answer1.equalsIgnoreCase("Q")) { break; }
//				else { System.out.println("Invalid response."); }
//			}
//		}
//		
//		try
//		{
//			udb.save();
//		} catch (FileNotFoundException e)
//		{
//			System.out.println("No User Database file found. Writing to new file.");
//		}
//		try
//		{
//			vdb.save();
//		} catch (FileNotFoundException e)
//		{
//			System.out.println("No Vehicle Database file found. Writing to new file.");
//		}
//		try
//		{
//			rdb.save();
//		} catch (FileNotFoundException e)
//		{
//			System.out.println("No Reservation Database file found. Writing to new file.");
//		}
//	}
//}