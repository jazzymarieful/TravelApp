package sr.unasat.travelapp.utilities;

import java.util.*;

public class RandomGenerator {

    private List<String> syllables = new ArrayList<>(Arrays.asList("ja", "zy", "ah", "mad", "sha",
            "vien", "shar", "ven", "cly", "de"));
    private List<String> syllablesLastName = new ArrayList<>(Arrays.asList("ma", "rie", "wir", "jo", "men",
            "go", "lo", "doe", "khie", "ford", "hu", "bard"));
    private List<String> nameSyllables;

    Random random = new Random();

    public String getRName() {
        nameSyllables = new ArrayList<>();
        Collections.shuffle(syllables);
//        System.out.println(syllables);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getRandomNumber(); i++) {
            nameSyllables.add(syllables.get(i));
//            System.out.println(nameSyllables.get(i));
            sb.append(nameSyllables.get(i));
        }
        String name = sb.toString();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public String getRLName() {
        nameSyllables = new ArrayList<>();
        Collections.shuffle(syllablesLastName);
//        System.out.println(syllablesLastName);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getRandomNumber(); i++) {
            nameSyllables.add(syllablesLastName.get(i));
//            System.out.println(nameSyllables.get(i));
            sb.append(nameSyllables.get(i));
        }
        String name = sb.toString();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void introduction() {
        System.out.println("Hi, mijn naam is " + getRName() + " " + getRLName());
    }

    public String getRPass() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toLowerCase();


        System.out.println(generatedString);

        return generatedString;
    }

    public float getRandomFloat() {
        float minAmount = 10.0f;
        float maxAmount = 100.0f;
        float randomAmount = Math.round((random.nextFloat() * (maxAmount - minAmount) + minAmount) * 10) / 10.0f;

        System.out.println(randomAmount);
        return randomAmount;
    }

    public int getRAge() {
        int min = 15;
        int max = 80;
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getRandomNumber() {
        int min = 2;
        int max = 4;
        return (int) ((Math.random() * (max - min)) + min);
    }

}
