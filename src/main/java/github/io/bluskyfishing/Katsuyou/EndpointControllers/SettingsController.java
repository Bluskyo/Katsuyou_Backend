package github.io.bluskyfishing.Katsuyou.EndpointControllers;

import github.io.bluskyfishing.Katsuyou.Methods.GetTag;
import github.io.bluskyfishing.Katsuyou.Models.Settings;
import github.io.bluskyfishing.Katsuyou.Services.SettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
public class SettingsController {

    private Settings currentSettings = new Settings();

    public Settings getCurrentSettings() {
        return currentSettings;
    }

    public void setCurrentSettings(Settings newSettings) {
        this.currentSettings = newSettings;
    }

    // applies settings from frontend.
    @PostMapping("/api/settings")
    public ResponseEntity<Settings> updateSettings (@RequestBody Settings settings) {
        if (settings != null){
            setCurrentSettings(settings);
            return ResponseEntity.status(HttpStatus.OK).body(settings);
        } else return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    // gets conjugation based on settings saved in backend.
    @PostMapping("/api/conjugation")
    public ResponseEntity<Map<String, String>> applySettings(
            // Get kanji from apply settings.
            @RequestHeader(value = "entry") String encodedEntry,
            @RequestHeader(value = "reading") String encodedReading,
            @RequestHeader(value = "pos") String encodedPos) {

        String entry = URLDecoder.decode(encodedEntry, StandardCharsets.UTF_8);
        String reading = URLDecoder.decode(encodedReading, StandardCharsets.UTF_8);
        String pos = URLDecoder.decode(encodedPos, StandardCharsets.UTF_8);
        String tag = GetTag.GetTagFromPos(pos);

        return ResponseEntity.status(HttpStatus.OK).body(new SettingsService().ConjugationBasedOnSettings(entry, reading, tag, getCurrentSettings()));
    }

}
