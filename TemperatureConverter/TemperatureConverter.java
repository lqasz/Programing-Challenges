import java.math.*;
import java.util.Scanner;

public class TemperatureConverter
{
	public void setTemperature(double value, String from, String to)
	{
		String name = "";
		double returnValue = 0;

		switch(from.toLowerCase()) {
			case "c":
				if(to.equalsIgnoreCase("K"))		returnValue = value + 273.15;
				else if(to.equalsIgnoreCase("F"))	returnValue = value * 1.8 + 32;
				else				returnValue = value;

				name = "Celsius";
				break;
			case "f":
				if(to.equalsIgnoreCase("K"))		returnValue = (value + 459.67) * 5/9;
				else if(to.equalsIgnoreCase("C"))	returnValue = (value - 32) * 5/9;
				else				returnValue = value;

				name = "Fahrenheit";
				break;
			case "k":
				if(to.equalsIgnoreCase("F"))		returnValue = value * 9/5 - 459.67;
				else if(to.equalsIgnoreCase("C"))	returnValue = value - 273.15;
				else				returnValue = value;

				name = "Kelvin";
				break;
			default:
				System.out.println("Wrong option!");
				break;
		}

		returnValue *= 100;
		returnValue = Math.round(returnValue);
		returnValue /= 100;

		System.out.println(value +" degrees "+ name +" = "+ returnValue +""+ to.toUpperCase());
	}

	public static void main(String[] args)
	{
		double value;
		String convertTo;
		String convertFrom;
		TemperatureConverter Test = new TemperatureConverter();

		convertFrom = getText("Convert from [K/C/F]: ");
		value = getNumber("Enter a temperature deegree/s: ");
		convertTo = getText("Convert to [K/C/F]: ");
		Test.setTemperature(value, convertFrom, convertTo);
	}

	public static String getText(String message)
	{
		System.out.print(message);
		Scanner Console = new Scanner(System.in);
		return Console.nextLine();
	}

	public static double getNumber(String message)
	{
		String text;
		
		System.out.print(message);
		Scanner Console = new Scanner(System.in);
		text = Console.nextLine();
		
		return Double.valueOf(text);
	}
}