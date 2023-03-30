public class DayMemory {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new CommandArgumentNotProvidedException("Translation file not specified.");
        }

        String languageCode = args[0];
        try {
            Translator translator = new Translator(languageCode);
            System.out.println(translator.translate(3, 8, 2021));
        } catch (ArgFileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
