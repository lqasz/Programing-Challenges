import java.util.*;

public class CalculateAge
{
	private boolean leapYear = false;

	public HashMap<Integer, Integer> makeDaysOfMonth(int year)
	{
		int swap = 1; // required for modulo operation
		HashMap<Integer, Integer> monthDays = new HashMap<Integer, Integer>();

		for(int i = 1; i <= 12; i++) {
			if(i == 7) {
				monthDays.put(i, 31);
				swap = 0;
			} else if(i % 2 == swap) {
				monthDays.put(i, 31); // months like 1, 3, 5, 8, 10, 12
			} else if(i == 2) {
				if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
					monthDays.put(i, 29);
					this.leapYear = true;
				} else {
					monthDays.put(i, 28);
				}
			} else {
				monthDays.put(i, 30); // months like 4, 6, 9, 11
			}
		}

		return monthDays; // days of month in specified year
	}

	public int calculateAgeInSeconds(int day, int month, int year)
	{
		int inSeconds = 0;
		HashMap<Integer, Integer> birthYear = this.makeDaysOfMonth(year); // days of the month in birth year

		// simple validation
		if(month < 13) {
			int dayOfBirth = birthYear.get(month);
        	if(day <= dayOfBirth) {
        		this.leapYear = false;
        		Date date = new Date();
				Calendar calendar = Calendar.getInstance();

				calendar.setTime(date);
				// get current day, month, year
				int currentYear = calendar.get(Calendar.YEAR);
				int currentMonth = calendar.get(Calendar.MONTH) + 1;
				int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

				HashMap<Integer, Integer> currentDays = this.makeDaysOfMonth(currentYear);
				int daysOfCurrentYear = (this.leapYear == false) ? 365 : 366; // if it is leap year then 366 else 365
				int daysOfThisYear = this.getDays(currentDays, currentMonth, currentDay); // return days left to the end of current year

				int daysUsedInCurrentYear = daysOfCurrentYear - daysOfThisYear; // days from 1 January to current day
				int daysOfFirstYear = this.getDays(birthYear, month, day); // days from birthday to 31 December
				
				int intervalYear = (daysOfFirstYear >= daysOfThisYear) ? currentYear - year : currentYear - year - 1; // we are after birthday or before?
				
				if(currentYear == year) {
					inSeconds = (daysOfFirstYear - daysOfThisYear) * 24 * 3600; // seconds from birthday to current day
				} else if(currentYear - 1 == year) {
					 inSeconds = (daysOfFirstYear + daysUsedInCurrentYear) * 24 * 3600; // seconds from days used in last year + days used in this year
				}else {
					int days = 0;

					// loop in collection which contains days = (birthday, current year)
					for(int i = year + 1; i <= (year + intervalYear) - 1; i++) {
						days += (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) ? 366 : 365;
					}

					inSeconds = (days + daysOfFirstYear + daysUsedInCurrentYear) * 24 * 3600;
				}
        	}
		}

		return inSeconds;
	}

	public int getDays(HashMap<Integer, Integer> daysOfYear, int month, int day)
	{
		int days = 0;

		for(int i = month; i <= 12; i++) {
			for(int j = (i == month) ? day : 1; j <= daysOfYear.get(i); j++) {
				days++;
			}
		}

		return days;
	}

	public static void main(String[] args)
	{
		CalculateAge Test = new CalculateAge();
		System.out.println(Test.calculateAgeInSeconds(11, 10, 1993) +" seconds");
	}
}