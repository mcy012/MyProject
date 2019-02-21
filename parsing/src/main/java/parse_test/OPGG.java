package parse_test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parse_test.dto.MyInfoDto;
import parse_test.dto.MyScoreDto;

import java.util.Scanner;

public class OPGG {

    /*
    public OPGG(){
        try {
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

    public MyInfoDto getMyInfo(String nickName) throws Exception {
        Document doc = Jsoup.connect("https://www.op.gg/summoner/userName=" + nickName).get();
        Elements contentsNick = doc.select("span.Name");
        nickName = contentsNick.text();

        Elements contentsTierRank = doc.select("div.TierRank");
        String tierRank = contentsTierRank.text();

        Elements contentsTierInfo = doc.select("div.TierInfo");
        String tierInfo = contentsTierInfo.text();

        Elements contentsWinRatio = doc.select("div.TierInfo span.winratio");
        String winRatio = contentsWinRatio.text();

        MyInfoDto myInfoDto = new MyInfoDto(nickName, tierRank, tierInfo, winRatio);

        Elements gameElements = doc.select("div.GameItemList > .GameItemWrap");

        for ( Element gameElement : gameElements ) {

            String gameType = gameElement.select(".GameType").text();
            String gameResult = gameElement.select(".GameResult").text();
            String champName = gameElement.select(".ChampionName > a").text();
            String kill = gameElement.select(".Kill").text();
            String death = gameElement.select(".Death").text();
            String assist= gameElement.select(".Assist").text();

            myInfoDto.addMyScore(new MyScoreDto(gameType, gameResult, champName, kill, death, assist));
        }
        //System.out.println(gameElements);
        return myInfoDto;
    }
}
