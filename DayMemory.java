public class DayMemory {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new CommandArgumentNotProvidedException("No language code provided.");
        }

        String languageCode = args[0];

        try {
            Translator translator = new Translator(languageCode);
            String output = translator.translate(3, 8, 2021);
            System.out.println(output);
        } catch (ArgFileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
