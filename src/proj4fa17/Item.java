package proj4fa17;
import java.util.Locale;
/**
 * <p> Title: Proj4fa17 <p>
 * <p> Description: The Item class is the mold for an item object. An object of type item must follow this class.
 * It has private variables so they cannot be modified by out side classes. However it does have accessor methods which
 * are our get methods.    <p> 
 * @author Edwin Orellana
 *
 */
public class Item 
{ 
	private String date;
	int totalOccupancy;
	private int totalAdult;
	private int totalMen;
	private int totalKids;
	private int totalWomen;
	private int families;
	/**
	 * Description: This is the default constructor which initializes
	 *  the private variables to their respective values.
	 */
	public Item()
	{
		date = new String();
		totalOccupancy = 0;
		totalAdult = 0;
		totalMen= 0;
		totalKids = 0;
		totalWomen = 0;
		families = 0;
		
	}
	/**
	 * parameterized constructor --
	 * initializes dayOfWeek, totalAdult, totalKids, totalOccupancy, totalMen, totalWomen, families
	 * to user specified values 
	 * @param day
	 * @param allAd
	 * @param allKids
	 * @param occupancy
	 * @param allM
	 * @param allF
	 * @param family
	 */
	public Item(String day, int allAd, int allKids, int occupancy, int allM, int allF, int family) 
	{
		date = new String(day);
		totalAdult = allAd;
		totalKids = allKids;
		totalOccupancy = occupancy;
		totalMen = allM;
		totalWomen = allF;
		families = family;
	}

	/**
	 * getTotalAdult method-- Gets the total amount of Adults
	 * @return total amount of adults
	 */
	public int getTotalAdult() 
	{
		return totalAdult;
	}
	/**
	 * getTotalKids method-- Gets the total amount of Kids
	 * @return amount of kids
	 */
	public int getTotalKids()
	{
		return totalKids;
	}
	/**
	 * getTotalOccupancy method-- Gets the total amount of people staying at the shelter
	 * @return total amount of people at the shelter
	 */
	public int getTotalOccupancy() 
	{
		return totalOccupancy;
	}
	/**
	 * getDay method-- Gets the date 
	 * @return the date
	 */
	public String getDay()
	{
		return  date;
	}
	/**
	 * getTotalWOmen method-- Gets the total amount of Women
	 * @return the total amount of women
	 */
	public int getTotalWomen() 
	{
		return totalWomen;
	}
	/**
	 * getTotalMen method-- Gets the total amount of Men
	 * @return the total amount of men
	 */
	public int getTotalMen()
	{
		return  totalMen;
	}
	/**
	 * getFamilies method-- Gets the total amount of families
	 * @return the total amount of Families
	 */
	public int getFamilies() 
	{
		return families;
	}
	/** getRatio method-- What this method does is it takes in two values passed in by the user
	 * it will then do the math and will keep the decimals however instead of giving all of them 
	 * it will only give to the tenth place.
	 * 
	 * @param allM
	 * @param allF
	 * @return it will return data of type string and that string contains the quotient of the two passed in values with a decimal to the tenth place
	 */
	public String getRatio(double allM, int allF) 
	{
		String ratio = String.valueOf(allM/allF);
		if (ratio != null) {
			String result = ratio;
			int decimal = result.indexOf(".");
			if (decimal != -1) {
				String decimalString = result.substring(decimal + 1);
				if (decimalString.length() > 2) {
					result = ratio.substring(0, decimal + 2);
				} else if (decimalString.length() == 1) {
					result = String.format(Locale.ENGLISH, "%.2f",
							Double.parseDouble(ratio));
				}
			}
			return result;
		}
		return null;

	}

	/**toString method-- It will give you a line of text that the creator made to best fit his code
	 * this one specifically will give you the date, total Adult, total Kids, Total men, Total women. 
	 * @return It returns data of type string. It will give back a line of text. 
	 */
	public String toString()
	{
		return "Date: " + date + " Daily Amount of People: " + totalAdult + 
				" Daily Amount of Kids: " + totalKids + " Daily Amount of Men: "
				+  totalMen + " Daily Amount of Women " + totalWomen ;
	}

}
