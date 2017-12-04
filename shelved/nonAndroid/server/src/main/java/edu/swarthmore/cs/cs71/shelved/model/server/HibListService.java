package edu.swarthmore.cs.cs71.shelved.model.server;

import edu.swarthmore.cs.cs71.shelved.spark.PersistenceUtils;

public class HibListService {
    public HibList createList(String listName, boolean publicStatus) {
        HibList newList = new HibList();
        newList.resetName(listName);
        newList.setPublicStatus(publicStatus);
        PersistenceUtils.ENTITY_MANAGER.get().persist(newList);
        return newList;
    }
}
