import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private String title;
    private String text;
    private String category;


    public Game(String title, String text, String category) {
        this.title = title;
        this.text = text;
        this.category = category;
    }

    @Override
    public String toString() {
        return title + System.lineSeparator() + text;
    }
}
