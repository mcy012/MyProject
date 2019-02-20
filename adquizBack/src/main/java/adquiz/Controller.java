package adquiz;


import adquiz.dto.answer;
import adquiz.dto.quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private adquiz.dao.adquizDao adquizDao;

    //회원가입
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public Map signUp(@RequestParam Map valueMap) {

        adquizDao.userSignUp(valueMap);

        return valueMap;
    }

    //회원가입시 포인트 100 기본
    @RequestMapping(value = "/pointSignUp", method = RequestMethod.POST)
    public Map psignUp(@RequestParam Map valueMap) {

        adquizDao.pointSignUp(valueMap);

        return valueMap;
    }

    //로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody Map valueMap) {

        return adquizDao.userLogin(valueMap);
    }

    //내정보 가져오기
    @RequestMapping(value = "/myInfo/{myId}", method = RequestMethod.GET)
    public Map myInfo(@PathVariable(value = "myId") String myId){

        return adquizDao.userInfo(myId);
    }

    //물품정보 가져오기
    @RequestMapping(value = "/goodsList", method = RequestMethod.GET)
    public List<Map> list() throws Exception {

        return adquizDao.goodsList();
    }

    //물품 사서 재고 깎이기
    @RequestMapping(value = "/goodsStockBuy/{goodsSeq}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int put(@PathVariable(value = "goodsSeq") int goodsSeq) {

        return adquizDao.goodsStockBuy(goodsSeq);
    }

    //포인트 변화 할 때 사용
    @RequestMapping(value = "/pointUpdate/{pointId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int buy(@PathVariable(value = "pointId") String pointId, @RequestBody Map valueMap) {

        valueMap.put("pointId", pointId);

       return adquizDao.pointUpdate(valueMap);
    }

    //게임룸 들어갔을 때 남은 기회 -1
    @RequestMapping(value = "/chanceMinus/{myId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int chance(@PathVariable(value = "myId") String myId) {

        return adquizDao.chanceMinus(myId);
    }

    //물건을 교환했을 때 교환정보 테이블에 넣기
    @RequestMapping(value = "/buyInfo", method = RequestMethod.POST)
    public Map buyinfo(@RequestBody Map valueMap) {

        adquizDao.buyInfo(valueMap);

        return valueMap;
    }

    @RequestMapping(value = "/quizList", method = RequestMethod.GET)
    public List<quiz> quizList() throws Exception{

        return adquizDao.quizList();
    }





    //주관식 문제, 답 가져오기
    @RequestMapping(value = "/getQuiz", method = RequestMethod.GET)
    public Map get() throws Exception{

        return adquizDao.getQuiz();
    }


    @RequestMapping(value = "/selQuiz", method = RequestMethod.GET)
    public List<Map> sel() throws Exception{

        return adquizDao.selQuiz();
    }
}
