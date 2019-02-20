package practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private practice.dao.practiceDao practiceDao;

    @RequestMapping(value = "/prac", method = RequestMethod.GET)
    public List<Map> prac() throws Exception{
        return practiceDao.practiceInfo();
    }

    @RequestMapping(value = "/prac", method = RequestMethod.POST)
    public Map pracin(@RequestParam Map valueMap) {
        practiceDao.practiceInsert(valueMap);
        return valueMap;
    }
}
