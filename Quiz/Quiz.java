import java.util.Random;
import java.util.Scanner;

public class Quiz
{
	private int generatedNumber;
	private String responce;

	public Quiz(int option, int range)
	{
		this.responce = "";
		this.generatedNumber = -1;

		if(option == 1 || option == 2) {
			this.generatedNumber = this.generateNumber(range);
		}
	}

	public boolean findMyNumber(int number)
	{
		boolean result = false;
		if(number > this.generatedNumber) {
			this.responce = "Lower!";
		} else if(number < this.generatedNumber) {
			this.responce = "Higher!";
		} else {
			this.responce = "You were correct!";
			result = true;
		}

		return result;
	}

	public void matchOptions(String text)
	{
		String generatedText = this.generateText();

		if(text.equalsIgnoreCase(generatedText)) {
			this.responce = "You were correct!";
		} else {
			this.responce = "You were wrong!";
		}
	}
	
	public String generateText()
	{
		if(this.generatedNumber % 2 == 0) {
			return "Heads";		
		} else {
			return "Tails";
		}
	}

	public int generateNumber(int range)
	{
		Random Generator = new Random();
		return Generator.nextInt(range);
	}

	public int getGeneratedNumber()
	{
		return this.generatedNumber;
	}

	public String getResponce()
	{
		return this.responce;
	}

	public static void main(String[] args)
	{
		do {
			System.out.println("\n1 - Higher/Lower;");
			System.out.println("2 - Heads/Tails;");
			System.out.println("All other numbers - stop;");

			int option = getNumber("Which option do you choose: ");
			if(option > 3)	break;
			else if(option == 1) {
				int steps = 1;
				int range = getNumber("Which range do you choose: ");
				boolean finded = false;

				Quiz Test = new Quiz(option, range);
				System.out.println("\nLets start!");
				System.out.println("Type your number from <0, "+ range +">");

				while(finded != true) {
					int typedNumber = getNumber("Yours pick: ");
					finded = Test.findMyNumber(typedNumber);
					System.out.println(Test.getResponce());
					steps++;
				}

				System.out.println("You were needed "+ steps +" steps to find number "+ Test.getGeneratedNumber() +"!");

			} else {
				Quiz Test = new Quiz(option, 10);
				String typedText = getText("Type Heads or Tails: ");

				Test.matchOptions(typedText);
				System.out.println(Test.getResponce());
			}

		} while(true);

		System.out.println("The program stoped!");
	}

	public static String getText(String message)
	{
		System.out.println(message);
		Scanner Console = new Scanner(System.in);
		return Console.nextLine();
	}

	public static int getNumber(String message)
	{
		String text;
		
		System.out.print(message);
		Scanner Console = new Scanner(System.in);
		text = Console.nextLine();
		
		return Integer.valueOf(text);
	}
}