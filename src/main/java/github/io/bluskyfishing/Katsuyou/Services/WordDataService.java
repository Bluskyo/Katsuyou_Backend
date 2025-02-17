package github.io.bluskyfishing.Katsuyou.Services;

import github.io.bluskyfishing.Katsuyou.Data.*;
import github.io.bluskyfishing.Katsuyou.Controllers.SettingsController;
import github.io.bluskyfishing.Katsuyou.Methods.Conjugations;
import github.io.bluskyfishing.Katsuyou.Methods.GetJLPT;
import github.io.bluskyfishing.Katsuyou.Methods.GetTag;

import github.io.bluskyfishing.Katsuyou.Models.Kanji;
import github.io.bluskyfishing.Katsuyou.Models.Settings;
import github.io.bluskyfishing.Katsuyou.Repositories.KanjiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class WordDataService {

    private final SettingsController settingsController;

    // Constructor injection
    public WordDataService(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    private KanjiRepository kanjiRepository;

   @Autowired
    public void setKanjiRepository(KanjiRepository kanjiRepository) {
        this.kanjiRepository = kanjiRepository;
    }

    public Kanji getEntryByKanji(String kanji) {
       var findKanji = kanjiRepository.findEntryByKanji(kanji);
        if (!findKanji.isEmpty()) {
            return kanjiRepository.findEntryByKanji(kanji).getFirst();
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    public Kanji getKanjiBasedSettings() {
        Settings currentSettings = settingsController.getCurrentSettings();
        String JLPTLevel = GetJLPT.getJLPTLevel(currentSettings);

        String[] verbs;

        switch (JLPTLevel) {
            case "N5" ->
                verbs = N5.n5Verbs();
            case "N4" ->
                verbs = N4.n4Verbs();
            case "N3" ->
                verbs = N3.n3Verbs();
            case "N2" ->
                verbs = N2.n2Verbs();
            case "N1" ->
                verbs = N1.n1Verbs();
            default ->
                throw new IllegalArgumentException("Invalid JLPT level: " + JLPTLevel);
        }
        int random = new Random().nextInt(verbs.length);
        String kanji = verbs[random];

        return kanjiRepository.findEntryByKanji(kanji).getFirst();
    }

    public Map<String, Map<String, String>> getAllConjugations(String kanji) {

        Kanji word = this.getEntryByKanji(kanji);

        String reading = word.reading;
        // if multiple readings chooses first one.
        String oneReading = reading.split(",")[0];

        String tag = GetTag.GetTagFromPos(word);

        Map<String, Map<String, String>> allConjugationsDict = new HashMap<>();

        allConjugationsDict.put("present", Conjugations.present(kanji, oneReading, tag));
        allConjugationsDict.put("past", Conjugations.past(kanji, oneReading, tag));
        allConjugationsDict.put("teForm", Conjugations.teForm(kanji, oneReading, tag));
        allConjugationsDict.put("potential", Conjugations.potential(kanji, oneReading, tag));
        allConjugationsDict.put("volitional", Conjugations.volitional(kanji, oneReading, tag));
        allConjugationsDict.put("passive", Conjugations.passive(kanji, oneReading, tag));
        allConjugationsDict.put("causative", Conjugations.causative(kanji, oneReading, tag));
        allConjugationsDict.put("causativePassive", Conjugations.causativePassive(kanji, oneReading, tag));
        allConjugationsDict.put("imperative", Conjugations.imperative(kanji, oneReading, tag));
        allConjugationsDict.put("conditional", Conjugations.conditional(kanji, oneReading, tag));

        return allConjugationsDict;
    }

}