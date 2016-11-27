import java.util.Random;

public class NameGenerator
{
	private char[] chars;
	private char[] vowels;
	private char[] consonants;

	public NameGenerator()
	{
		this.chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'w', 'x', 'y', 'z'};
		this.vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
		this.consonants = new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 't', 'w', 'x', 'z'};
	}

	public void setCredintionals()
	{
		Random Generator = new Random();
		System.out.println("Name: "+ this.generateName(Generator));
		System.out.println("Password: "+ this.generatePassword(Generator));
	}

	public String generateName(Random Generator)
	{
		char[] name = new char[this.setStringLength(Generator)];
		boolean vowel = ((Generator.nextInt(1) + 1) % 2 == 0) ? true : false;

		for(int i = 0; i < name.length; i++) {
			if(vowel == true) {
				vowel = false;
				name[i] = this.vowels[Generator.nextInt(this.vowels.length - 1)];
			} else {
				name[i] = this.consonants[Generator.nextInt(this.consonants.length - 1)];
				vowel = true;
			}

			if(i == 0) {
				name[i] = Character.toUpperCase(name[i]);
			}
		}

		return String.valueOf(name);
	}

	public String generatePassword(Random Generator)
	{
		char[] name = new char[this.setStringLength(Generator)];

		for(int i = 0; i < name.length; i++) {
			int selectChar = Generator.nextInt(this.chars.length - 1);
			int upperCase = Generator.nextInt(9) + 1;

			name[i] = this.chars[selectChar];

			if(upperCase % 2 == 0) {
				name[i] = Character.toUpperCase(name[i]);
			} else if(upperCase % 3 == 0) {
				name[i] = (char)('0'+ Generator.nextInt(8) + 1);
			}
		}

		return String.valueOf(name);
	}

	public int setStringLength(Random Generator)
	{
		int length = (Generator.nextInt(this.chars.length) + 1) / 2;

		if(length < 5) {
			length += 7;
		}

		return length;
	}

	public static void main(String[] args) {
		NameGenerator Test = new NameGenerator();
		Test.setCredintionals();
	}
}