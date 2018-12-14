package adquiz.dao;

import adquiz.dto.quiz;

import java.util.List;
import java.util.Map;

public interface adquizDao {

    Map userLogin(Map valueMap);

    void pointSignUp(Map valueMap);

    void userSignUp(Map valueMap);

    Map userInfo(String myId);

    int goodsStockBuy(int goodsSeq);

    int pointUpdate(Map valueMap);

    int chanceMinus(String myId);

    List<Map> goodsList();

    void buyInfo(Map valueMap);

    List<quiz> quizList();

    Map getQuiz();

    List<Map> selQuiz();
}
