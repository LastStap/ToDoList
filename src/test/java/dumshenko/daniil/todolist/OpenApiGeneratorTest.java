package dumshenko.daniil.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OpenApiGeneratorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void generateOpenApiSpec() throws Exception {
        String apiDocs = mockMvc.perform(get("/api-docs.yaml"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Files.writeString(
                Paths.get("src/main/resources/api.yml"),
                apiDocs
        );
    }
}