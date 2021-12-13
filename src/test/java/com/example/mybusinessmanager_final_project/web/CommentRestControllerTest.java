package com.example.mybusinessmanager_final_project.web;

import com.example.mybusinessmanager_final_project.model.binding.NewCommentBindingModel;
import com.example.mybusinessmanager_final_project.model.entity.CommentEntity;
import com.example.mybusinessmanager_final_project.model.entity.ReportEntity;
import com.example.mybusinessmanager_final_project.model.entity.UserEntity;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportStatusEnum;
import com.example.mybusinessmanager_final_project.model.entity.enums.ReportTypeEnum;
import com.example.mybusinessmanager_final_project.repository.ReportRepository;
import com.example.mybusinessmanager_final_project.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("admin")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

    private static final String COMMENT_1 = "Comment 1";
    private static final String COMMENT_2 = "Comment 2";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();

        testUser.setUsername("teo")
                .setPassword("11111")
                .setEmail("teo@teo")
                .setActive(true)
                .setFirstName("teo")
                .setLastName("todorov");


        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        reportRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        var report = initComments(initReport());

        mockMvc.perform(get("/api/" + report.getId() + "/comments")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testNewComments() throws Exception {
        NewCommentBindingModel testComment = new NewCommentBindingModel().
                setMessage(COMMENT_1);

        var emptyReport = initReport();

        mockMvc.perform(
                        post("/api/" + emptyReport.getId() + "/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(testComment))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyReport.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }

    private ReportEntity initReport() {
        ReportEntity testReport = new ReportEntity();
        testReport.setName("report 1")
                .setReportStatusEnum(ReportStatusEnum.CHECKED)
                .setReportTypeEnum(ReportTypeEnum.REPORT)
                .setDescription("lorem...")
                .setAuthor(testUser);

        return reportRepository.save(testReport);
    }

    private ReportEntity initComments(ReportEntity report) {

        CommentEntity comment1 = new CommentEntity();
        comment1.setCreated(Instant.now());
        comment1.setAuthor(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);
        comment1.setReportEntity(report);

        CommentEntity comment2 = new CommentEntity();
        comment2.setCreated(Instant.now());
        comment2.setAuthor(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);
        comment2.setReportEntity(report);

        report.setComments(List.of(comment1, comment2));

        return reportRepository.save(report);
    }
}