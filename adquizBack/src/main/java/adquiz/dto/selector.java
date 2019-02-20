package adquiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})

public class selector {
    private int selectorSeq;
    private int quizSeq;
    private String selectorContent;

    public int qetSeletorSeq(){
        return selectorSeq;
    }
    public void setSelectorSeq(int selectorSeq) {
        this.selectorSeq = selectorSeq;
    }

    public int qetQuizSeq(){
        return quizSeq;
    }
    public void setQuizSeq(int quizSeq) {
        this.quizSeq = quizSeq;
    }

    public String qetSelectorContent(){
        return selectorContent;
    }
    public void setSelectorContent(String selectorContent) {
        this.selectorContent= selectorContent;
    }


}
