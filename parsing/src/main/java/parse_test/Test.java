package parse_test;

import parse_test.dto.MyInfoDto;

public class Test {

    public static void main(String[] args) throws Exception {
        OPGG opgg = new OPGG();
        MyInfoDto myInfoDto = opgg.getMyInfo("hideonbush");
        System.out.print(myInfoDto.getMyScoreDtoList());
    }
}
