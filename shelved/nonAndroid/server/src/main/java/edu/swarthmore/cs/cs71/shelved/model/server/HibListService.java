package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.spark.PersistenceUtils;

public class HibListService {
    public HibReadingList createList(String listName, boolean publicStatus) {
        HibReadingList newList = new HibReadingList();
        newList.resetName(listName);
        newList.setPublicStatus(publicStatus);
        PersistenceUtils.ENTITY_MANAGER.get().persist(newList);
        return newList;
    }
}
