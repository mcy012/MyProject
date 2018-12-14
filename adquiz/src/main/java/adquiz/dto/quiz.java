package adquiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class quiz {
    private int quizSeq;
    private String quizContent;
    private List<selector> selectorList;
    private List<answer> answerList;

    public int getQuizSeq(){
        return quizSeq;
    }
    public  void setQuizSeq(int quizSeq) {
        this.quizSeq = quizSeq;
    }

    public String getQuizContent(){
        return quizContent;
    }
    public void setQuizContent(String quizContent){
        this.quizContent = quizContent;
    }

    public List<selector> getSelectorList() {
        return selectorList;
    }
    public void setSelectorList(List<selector> selectorList) {
        this.selectorList = selectorList;
    }

    public List<answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<answer> answerList) {
        this.answerList = answerList;
    }

}
