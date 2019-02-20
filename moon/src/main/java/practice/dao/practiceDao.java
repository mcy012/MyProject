package practice.dao;

import java.util.List;
import java.util.Map;

public interface practiceDao {
    List<Map> practiceInfo();

    void practiceInsert(Map valueMap);
}
