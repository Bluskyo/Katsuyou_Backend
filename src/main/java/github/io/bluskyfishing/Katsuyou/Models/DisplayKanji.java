package github.io.bluskyfishing.Katsuyou.Models;

public class DisplayKanji {
    public String kanji;
    public String hiragana;
    public String tag;
    public String meaning;

    public DisplayKanji(String kanji, String hiragana, String tag, String meaning) {
        this.kanji = kanji;
        this.hiragana = hiragana;
        this.tag = tag;
        this.meaning = meaning;
    }
}
