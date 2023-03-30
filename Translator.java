import java.io.*;

public class Translator {
    private TranslationText translationText;
    private String languageCode;

    public Translator(String languageCode) {
        if (!languageCode.matches("^[a-z]{2}-[A-Z]{2}$")) {
            throw new IllegalArgumentException("Invalid language code format.");
        }
        this.languageCode = languageCode;
        importTranslation();
    }

    public TranslationText getTranslation() {
        return translationText;
    }

    private void importTranslation() {
        try {
            deserialize();
        } catch (IOException | ClassNotFoundException e) {
            try {
                importFromText();
                serialize();
            } catch (IOException ex) {
                throw new ArgFileNotFoundException("File not found: " + languageCode + ".txt");
            }
        }
    }

    private void importFromText() throws IOException {
        String fileName = languageCode + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] months = new String[12];
            String[] days = new String[31];
            for (int i = 0; i < 12; i++) {
                months[i] = reader.readLine();
            }
            for (int i = 0; i < 31; i++) {
                days[i] = reader.readLine();
            }
            String sentence = reader.readLine();
            translationText = new TranslationText(months, days, sentence);
        }
    }

    private void serialize() throws IOException {
        String fileName = languageCode + ".ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(translationText);
        }
    }

    private void deserialize() throws IOException, ClassNotFoundException {
        String fileName = languageCode + ".ser";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            translationText = (TranslationText) ois.readObject();
        }
    }

    public String translate(int month, int day, int year) {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid month or day.");
        }
        String monthString = translationText.getMonth(month - 1);
        String dayString = translationText.getDay(day - 1);
        return String.format(translationText.getSentence(), dayString, monthString, year);
    }
}
