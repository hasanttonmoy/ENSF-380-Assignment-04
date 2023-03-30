import java.io.Serializable;

public class TranslationText implements Serializable {
    private static final long serialVersionUID = 19L;

    private String[] months;
    private String[] days;
    private String sentence;

    public TranslationText(String[] months, String[] days, String sentence) {
        this.months = months;
        this.days = days;
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }

    public String[] getMonths() {
        return months;
    }

    public String[] getDays() {
        return days;
    }

    public String getMonth(int index) {
        return months[index];
    }

    public String getDay(int index) {
        return days[index];
    }
}
