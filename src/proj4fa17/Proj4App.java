package proj4fa17;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * <p> Title: Proj4fa17<p>
 * <p> Description:The Proj4App class finds the file you choose. It then reads the file line by line storing information that we will be using
 * through out this project. We make an ArrayUnorderedList object which is of type Item. This LabApp will be using methods found within the Item class,
 * also brings up prompts for the user to interact with depending on what they choose they get a different response. If they click the first option 
 * they will get a choice between the ratio of men and women, as well as men to children. The second option checks which checks for the busiest month 
 * within the past 3 years how this was done was with a for loop and a lot of if statements making sure we got the avg total occupancy for each month.
 *  The third option given allows the user to compare Families with children to single adult, women, and the total occupancy of shelters.   <p>
 * @author Edwin Orellana
 *
 */
public class Proj4App 
{
	public static void main(String args[])
	{
		ArrayUnorderedList<Item> list = new ArrayUnorderedList<Item>();

		//Input file which needs to be parsed
		String fileToParse = "DHS_Daily_Report.csv";
		File file = new File(fileToParse);
		try 
		{
			Scanner input = new Scanner(file);
			input.useDelimiter(","); //This is how we know to break the code up
			int sumOfAllM = 0;
			int numOfLines = 0;
			int sumOfAllF = 0;
			int sumOfAllAdults = 0;
			int sumOfAllKids = 0;
			int sumOfAllFamily = 0;
			int sumOfAll = 0;
			input.nextLine();//Skip first line since we don't want the titles just the information.
			while(input.hasNext()) // As long there has there is a next keep storing the info into the specified destination
			{

				String date = input.next();
				int totalA = Integer.parseInt(input.next());
				int child = Integer.parseInt(input.next());
				int occupancy = Integer.parseInt(input.next());
				int allM = Integer.parseInt(input.next());
				int allF = Integer.parseInt(input.next());
				for(int i = 0; i < 4; i++ ) { //The information needed for total families is near the end so this is skipping to that information
					input.next();
				}
				int families = Integer.parseInt(input.next());
				sumOfAllAdults += totalA;
				sumOfAllKids += child;
				sumOfAll += occupancy;
				sumOfAllM += allM;
				sumOfAllF += allF;
				sumOfAllFamily += families;
				numOfLines ++;
				input.nextLine();
				Item items = new Item(date, totalA, child, occupancy, allM, allF, families);
				list.addToRear(items);
				//System.out.println(items.toString());
			}
			//getting all the averages we will be using.
			double avgAdult = ((double)sumOfAllAdults/numOfLines);
			int avgK = sumOfAllKids/numOfLines;
			int avgOccupancy = sumOfAll/numOfLines;
			int avgM =sumOfAllM/numOfLines;
			int avgF = sumOfAllF/numOfLines;
			int avgFamilies = sumOfAllFamily/numOfLines;
			
			// here is the start of our selection menu
			int menu = 0;
			boolean exit = false;
			while(menu != 3 && exit == false) 
			{
				String[] options = {"Ratios","Busiest Month", "Families With Kids" , "Quit"};
				menu = JOptionPane.showOptionDialog(
						null,
						"Please Select One That You're Interested In ",
						"DHS Daily Reports",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
				Item items = new Item();
				if (menu == -1) {
					exit = true;
					System.out.println("Left the Program");
				}
				else if(menu == 3) 
				{
					exit = true;
					System.out.println("Left the Program");
				}
				else if(menu == 0) 
				{
					//making it so the user has more options to choose from once the first button is clicked
					int newMenu = 0;
					boolean exited = false;
					while(newMenu != 2 && exited == false) 
					{
						String[] choice = {"Men to Women", "Total Adults to Children", "Quit"};
						newMenu = JOptionPane.showOptionDialog(
								null,
								"Please Select One That You're Interested In ",
								"DHS Daily Reports",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null, choice, choice[0]);

						if(newMenu == -1)
						{
							exited = true;
							System.out.println("Left Ratio Section");
						}
						else if (newMenu ==0)
						{
							System.out.println("Men,Women");
							//getting all the information within a specific columns that we need.
							int colNo = 1;
							for ( int i = 0; i < list.size(); i++ ) 
							{
								if ( i % colNo == 0 ) 
								{
									System.out.println(list.get(i).getTotalMen() + "," + list.get(i).getTotalWomen());
								}
							}
							//Getting the ratio that was selected
							JOptionPane.showMessageDialog(null, ("Ratio of men to women in shelter \n"
									+ "                    " + items.getRatio(avgM, avgF) + ":1"), 
									"Daily Homeless Report", JOptionPane.INFORMATION_MESSAGE);

						}
						else if(newMenu == 1) 
						{
							System.out.println("Adults,Kids");
							int colNo =1;
							for ( int i = 0; i < list.size(); i++ ) 
							{
								if ( i % colNo == 0) 
								{
									System.out.println(list.get(i).getTotalAdult() + "," + list.get(i).getTotalKids());
								}
							}
							JOptionPane.showMessageDialog(null, ("Ratio of all Adults to All Kids \n                   " + items.getRatio(avgAdult, avgK) + ":1"), "Daily Homless Report", JOptionPane.INFORMATION_MESSAGE);

						}
					}
				}

				else if(menu == 1) 
				{	//Again making it so the user has more options once option 1 is clicked
					int newerMenu = 0;
					boolean left = false;
					while( newerMenu != 3 && left == false ) {
						String[] choice = {"Year 2015", " Year 2016", " Year 2017", "Quit"};
						newerMenu = JOptionPane.showOptionDialog(
								null,
								"Please Select Which Year You Want To See ",
								"DHS Daily Reports",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null, choice, choice[0]);

						// local variables for each month was made if I made a universal one each time I ran the code the avg increased instead of staying fixed
						if(newerMenu == -1)
						{
							left = true;
							System.out.println("Left Busiest Month Section");
						}
						else if(newerMenu == 0) 
						{	
							int jan=0;
							int feb = 0;
							int mar = 0;
							int apr = 0;
							int may = 0;
							int june = 0;
							int july = 0;
							int aug = 0;
							int sept = 0;
							int oct = 0;
							int nov = 0;
							int dec = 0;
							int colNo =1;
							for ( int i = 0; i < 356; i++ ) 
							{
								if ( i % colNo == 0) 
								{
									System.out.println(list.get(i).getDay() + "," + list.get(i).getTotalOccupancy());
								}
							}
							// Reading the lines that go till the end of the year. This is 2015
							for(int i = 0; i < 358; i++)
							{
								// Reading till the end of January stopping but 
								//while it was reading the lines of code we were getting the Total amount of people each day
								//We will repeat this process for each month and for the three years
								if(i < 31)
								{
									int janu = list.get(i).getTotalOccupancy();
									jan += janu;
								}
								else if (i > 31 && i < 58 ) 
								{
									feb = list.get(i).getTotalOccupancy();
								}
								else if(i > 57 && i < 86)
								{
									mar = list.get(i).getTotalOccupancy();

								}
								else if(i > 85 && i < 115)
								{
									apr = list.get(i).getTotalOccupancy();

								}
								else if(i > 114 && i < 142)
								{
									may = list.get(i).getTotalOccupancy();

								}
								else if(i > 141 && i < 172)
								{
									june = list.get(i).getTotalOccupancy();

								}
								else if(i > 170 && i < 202)
								{
									july = list.get(i).getTotalOccupancy();

								}
								else if(i > 201 && i < 234)
								{
									aug = list.get(i).getTotalOccupancy();

								}
								else if(i > 233 && i < 265)
								{
									sept = list.get(i).getTotalOccupancy();
								}
								else if(i > 264 && i < 296)
								{
									oct = list.get(i).getTotalOccupancy();
								}
								else if(i > 295 && i < 325)
								{
									nov = list.get(i).getTotalOccupancy();
								}
								else if(i > 324 && i < 355)
								{
									dec = list.get(i).getTotalOccupancy();
								}
							}
							JOptionPane.showMessageDialog(null, ("The most occupied month for the year 2015 was: \n Janurary With an Avg Occupancy Of: " + (jan/31)));
						}
						if(newerMenu == 1) 
						{
							int jan=0;
							int feb = 0;
							int mar = 0;
							int apr = 0;
							int may = 0;
							int june = 0;
							int july = 0;
							int aug = 0;
							int sept = 0;
							int oct = 0;
							int nov = 0;
							int dec = 0;
							int colNo =1;
							for ( int i = 356; i < 721; i++ ) 
							{
								if ( i % colNo == 0) 
								{
									System.out.println(list.get(i).getDay() + "," + list.get(i).getTotalOccupancy());
								}
							}
							for(int i = 355; i < 722; i++)
							{
								if(i > 355 && i < 386)
								{
									int janu = list.get(i).getTotalOccupancy();
									jan += janu;

								}
								else if (i > 386 && i < 415 ) 
								{
									int febu = list.get(i).getTotalOccupancy();
									feb += febu;
								}
								else if(i > 415 && i < 447)
								{
									int march = list.get(i).getTotalOccupancy();
									mar += march;
								}
								else if(i > 446 && i < 475)
								{
									int april = list.get(i).getTotalOccupancy();
									apr += april;
								}
								else if(i > 473 && i < 505)
								{
									int mayo = list.get(i).getTotalOccupancy();
									may += mayo;
								}
								else if(i > 505 && i < 535)
								{
									int jun = list.get(i).getTotalOccupancy();
									june += jun;
								}
								else if(i > 534 && i < 565)
								{
									int jul = list.get(i).getTotalOccupancy();
									july += jul;
								}
								else if(i > 564 && i < 595 )
								{
									int august = list.get(i).getTotalOccupancy();
									aug += august;
								}
								else if(i > 595 && i < 626)
								{
									int september = list.get(i).getTotalOccupancy();
									sept += september;

								}
								else if(i > 624 && i < 659)
								{
									int octobor = list.get(i).getTotalOccupancy();
									oct += octobor;
								}
								else if(i > 658 && i < 689)
								{
									int november = list.get(i).getTotalOccupancy();
									nov += november;
								}
								else if(i > 688 && i < 720)
								{
									int december = list.get(i).getTotalOccupancy();
									dec += december;
								}
							}	
							JOptionPane.showMessageDialog(null, ("The busiest month for the year 2016 was: \n November With an Avg Occupancy Of: " + (nov/31)));
						}

						
							else if(newerMenu == 2) {
								int jan=0;
								int feb = 0;
								int mar = 0;
								int apr = 0;
								int may = 0;
								int june = 0;
								int july = 0;
								int aug = 0;
								int sept = 0;
								int oct = 0;
								int nov = 0;
								int dec = 0;
								int colNo =1;
								for ( int i = 719; i < 1040; i++ ) 
								{
									if ( i % colNo == 0) 
									{
										System.out.println(list.get(i).getDay() + "," + list.get(i).getTotalOccupancy());
									}
								}
								for(int i = 719; i < 1039; i++)
								{
									if(i > 720 && i < 747)
									{
										int janu = list.get(i).getTotalOccupancy();
										jan += janu;

									}
									else if (i > 746 && i < 775 ) 
									{
										int febu = list.get(i).getTotalOccupancy();
										feb += febu;
									}
									else if(i > 774 && i < 806)
									{
										int march = list.get(i).getTotalOccupancy();
										mar += march;
									}
									else if(i > 805 && i < 831)
									{
										int april = list.get(i).getTotalOccupancy();
										apr += april;
									}
									else if(i > 830 && i < 856)
									{
										int mayo = list.get(i).getTotalOccupancy();
										may += mayo;

									}
									else if(i > 856 && i < 892)
									{
										int jun = list.get(i).getTotalOccupancy();
										june += jun;
									}
									else if(i > 891 && i < 923)
									{
										int jul = list.get(i).getTotalOccupancy();
										july += jul;


									}
									else if(i > 922 && i < 954 )
									{
										int august = list.get(i).getTotalOccupancy();
										aug += august;


									}
									else if(i > 953 && i < 985)
									{
										int september = list.get(i).getTotalOccupancy();
										sept += september;


									}
									else if(i > 984 && i < 1016)
									{
										int octobor = list.get(i).getTotalOccupancy();
										oct += octobor;

									}
									else if(i > 1015 && i < 1041)
									{
										int november = list.get(i).getTotalOccupancy();
										nov += november;
									}

								}	
								JOptionPane.showMessageDialog(null, ("The busiest month for the year 2017 was: \n September With an Avg Occupancy Of: " + (sept/30)));
							}

						}
					}
				// This is our last analysis it gives us the Total amount of people that are in Families that have children in it.
				//The user is given the option to compare them to single men,women, and the total occupancy of shelters.
					else if(menu == 2) 
					{
						int lastMenu = 0;
						boolean quit = false;
						while(lastMenu != 3 && quit == false) {
						String[] choice = {"Single Men" , "Single Women", "Total People Staying In Shelters", "Quit"};
						lastMenu = JOptionPane.showOptionDialog(
								null,
								avgFamilies + " People have Families with children in a shelter would you like to compare it to ",
								"DHS Daily Reports",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null, choice, choice[0]);
						if(lastMenu == -1)
						{
							quit = true;
							System.out.println("Left Families With Children Comparison");
						}
						else if(lastMenu == 0) 
						{
							int colNo =1;
							for ( int i = 0; i < list.size(); i++ ) 
							{
								if ( i % colNo == 0) 
								{
									System.out.println(list.get(i).getFamilies() + "," + list.get(i).getTotalMen());
								}
							}
						JOptionPane.showMessageDialog(null,("The Ratio of Familes With Children to Single Men is: \n" + items.getRatio(avgFamilies, avgM) + ":1"));	
						}
						else if(lastMenu == 1)
						{
							int colNo =1;
							for ( int i = 0; i < list.size(); i++ ) 
							{
								if ( i % colNo == 0) 
								{
									System.out.println(list.get(i).getFamilies() + "," + list.get(i).getTotalWomen());
								}
							}
							JOptionPane.showMessageDialog(null, ("The Ratio of Familes with Children to Single Women is \n" + items.getRatio(avgFamilies, avgF) + ":1"));
						}
						else if(lastMenu == 2) 
						{
							int colNo =1;
							for ( int i = 0; i < list.size(); i++ ) 
							{
								if ( i % colNo == 0) 
								{
									System.out.println(list.get(i).getFamilies() + "," + list.get(i).getTotalOccupancy());
								}
							}
							JOptionPane.showMessageDialog(null,  ("The Ratio Of Families with Children to Total Occupancy \n" + items.getRatio(avgFamilies, avgOccupancy) + ":1"));
						}
					}
				}
			}
		}
		//Make sure the catch an error if the file was not found
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}

	}


