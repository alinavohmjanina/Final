import com.weather.File;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    @Test
    void testWriteRead() {
        File file = new File();
      //  File.mock();

        // Test data
        List<String> writeLines = new ArrayList<>();
        writeLines.add("Tallinn,EE");
        writeLines.add("Helsinki,FI");

        try {
            assertTrue(file.write(writeLines, "testfile.txt"));

            List<String> lines = file.read("testfile.txt");

            assertEquals("Tallinn,EE", lines.get(0));
            assertEquals("Helsinki,FI", lines.get(1));
        } catch (IOException e) {
            fail(e);
        }
    }

}