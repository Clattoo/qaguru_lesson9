import com.fasterxml.jackson.databind.ObjectMapper;
import model.Example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingJsonTest {

    private final ClassLoader cl = ParsingJsonTest.class.getClassLoader();
    public final String zipName = "lesson9.zip";

    @Test
    @DisplayName("Проверка JSON-файла из архива lesson9.zip")
    void jsonParsingFileTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("example.json"))) {
            ObjectMapper mapper = new ObjectMapper();
            Example actualData = mapper.readValue(reader, Example.class);

            // Проверяем данные
            assertThat(actualData.isCanBeRotated()).isFalse();
            assertThat(actualData.isSearchVoiceClickSoundEnabled()).isTrue();

            assertThat(actualData.getItems().get(0).getId()).isEqualTo(1);
            assertThat(actualData.getItems().get(0).getName()).isEqualTo("Item 1");
            assertThat(actualData.getItems().get(0).getDescription())
                    .isEqualTo("This is the description for item 1.");

            assertThat(actualData.getItems().get(1).getId()).isEqualTo(2);
            assertThat(actualData.getItems().get(1).getName()).isEqualTo("Item 2");
            assertThat(actualData.getItems().get(1).getDescription())
                    .isEqualTo("This is the description for item 2.");

            assertThat(actualData.getItems().get(2).getId()).isEqualTo(3);
            assertThat(actualData.getItems().get(2).getName()).isEqualTo("Item 3");
            assertThat(actualData.getItems().get(2).getDescription())
                    .isEqualTo("This is the description for item 3.");
        }
    }
}