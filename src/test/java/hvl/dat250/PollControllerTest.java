package hvl.dat250;

import hvl.dat250.model.Poll;
import hvl.dat250.service.PollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PollService pollService;

    private Poll poll;

    @BeforeEach
    void setUp() {
        poll = new Poll();
        poll.setId("123");
        poll.setQuestion("Sample Question?");
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnPollsForUserRole() throws Exception {

        when(pollService.getAllPolls()).thenReturn(List.of(poll));

        mockMvc.perform(get("/api/polls"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnPollsForAdminRole() throws Exception {

        when(pollService.getAllPolls()).thenReturn(List.of(poll));

        mockMvc.perform(get("/api/polls"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void shouldReturnForbiddenForGuestRole() throws Exception {

        mockMvc.perform(get("/api/polls"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnUnauthorizedForNoUser() throws Exception {
        mockMvc.perform(get("/api/polls"))
                .andExpect(status().isForbidden());
    }
}
