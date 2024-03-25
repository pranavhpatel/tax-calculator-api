import org.junit.jupiter.api.Test;
import org.pranav.handlers.DownStreamDataLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class DownStreamDataLoaderTest {
    @Test
    public void testGetResponse() throws IOException {
        // mock connection
        HttpURLConnection connection = mock(HttpURLConnection.class);
        when(connection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // read from resources the sample test
        InputStream inputStream = getClass().getResourceAsStream("/sample_response.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // get that data when called
        when(connection.getInputStream()).thenReturn(inputStream);

        // test
        StringBuilder response = DownStreamDataLoader.getResponse();
        String expectedResponse = "{\"tax_brackets\":[" +
                "{\"max\":50197,\"min\":0,\"rate\":0.15}," +
                "{\"max\":100392,\"min\":50197,\"rate\":0.205}," +
                "{\"max\":155625,\"min\":100392,\"rate\":0.26}," +
                "{\"max\":221708,\"min\":155625,\"rate\":0.29}," +
                "{\"min\":221708,\"rate\":0.33}]}";
        assertEquals(expectedResponse, response.toString());
    }
}
