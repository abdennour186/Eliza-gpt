package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherResponseHandlerTest {

    TeacherResponseHandler teacherResponseHandler;

    @BeforeEach
    public void setUp(){
        teacherResponseHandler = new TeacherResponseHandler();
    }

    @Test
    void generateResponse() {
        String input = "Qui est le plus intelligent ?";
        String expected_res  = "Le plus intelligent est bien sûr votre enseignant de MIF01!";
        String result = teacherResponseHandler.handleResponse(input);

        assertEquals(result,expected_res);
    }
}