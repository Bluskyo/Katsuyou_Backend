package github.io.bluskyfishing.Katsuyou.Controllers;
import github.io.bluskyfishing.Katsuyou.Models.Kanji;
import github.io.bluskyfishing.Katsuyou.Services.WordDataService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class WordDataController {

    private final WordDataService wordDataService;

    // Constructor injection for WordDataService
    public WordDataController(WordDataService wordDataService) {
        this.wordDataService = wordDataService;
    }

    @GetMapping("/api/v1/{kanji}")
    public ResponseEntity<Kanji> getEntryByKanji(@PathVariable("kanji") String kanji) {
        return ResponseEntity.status(HttpStatus.OK).body(wordDataService.getEntryByKanji(kanji));
    }

    @GetMapping("/api/v1/allConjugations/{kanji}")
    public ResponseEntity<Map<String, Map<String, String>>> getAllConjugations(@PathVariable("kanji") String kanji){
        return ResponseEntity.status(HttpStatus.OK).body(wordDataService.getAllConjugations(kanji));
    }

    @GetMapping("/api/v1/random")
    public Kanji getRandomKanji() {
        return wordDataService.getKanjiBasedSettings();
    }
    
}
