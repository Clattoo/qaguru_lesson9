import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParsingFilesTest {

    private final ClassLoader cl = ParsingFilesTest.class.getClassLoader();
    public final String zipName = "lesson9.zip";

    @Test
    @DisplayName("Проверка PDF-файла из архива lesson9.zip")
    void zipParsingPdfTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(zipName))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("user_interface_design.pdf")) {
                    PDF pdf = new PDF(zis);

                    assertThat(pdf.numberOfPages).isEqualTo(7);
                    assertThat(pdf.producer).isEqualTo("PDFTron PDFNet, V8.1177\n");

                }
            }
        }
    }

    @Test
    @DisplayName("Проверка XLS-файла из архива lesson9.zip")
    void zipParsingXlsTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(zipName))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Test.xlsx")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

                    assertTrue(actualValue.contains("Проверка"));
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка CSV-файла из архива lesson9.zip")
    void zipParsingCsvTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream(zipName))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("example.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));

                        List<String[]> data = reader.readAll();
                        assertEquals(4, data.size());
                        assertArrayEquals(
                                new String[] {"John Doe", "Designer", "325 Pine Street", "", "Seattle"},
                                data.get(1)
                        );
                        assertArrayEquals(
                                new String[] {"Edward Green", "Developer", "110 Pike Street", "WA", "Seattle"},
                                data.get(3)
                        );
                    }
                }
            }
        }
}
