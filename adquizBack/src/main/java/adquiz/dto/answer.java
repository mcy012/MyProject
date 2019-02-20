package adquiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class answer {
    private int answerSeq;
    private int quizSeq;
    private String answerContent;

    public int qetAnswerSeq(){
        return answerSeq;
    }
    public void setAnswerSeq(int answerSeq) {
        this.answerSeq = answerSeq;
    }

    public int qetQuizSeq(){
        return quizSeq;
    }
    public void setQuizSeq(int quizSeq) {
        this.quizSeq = quizSeq;
    }

    public String qetAnswerContent(){
        return answerContent;
    }
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}
