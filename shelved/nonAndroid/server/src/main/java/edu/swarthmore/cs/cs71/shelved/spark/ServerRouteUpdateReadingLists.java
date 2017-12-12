package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibListService;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleReadingList;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ReadingListsUpdate.ValidReadingListsUpdateResponse;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

import java.util.List;

public class ServerRouteUpdateReadingLists extends ServerRoute {
    public ServerRouteUpdateReadingLists(SessionFactory sf) { super(sf); }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        HibListService service = new HibListService();
        List<SimpleReadingList> readingLists = service.getAllReadingLists(getSf());
        return new ValidReadingListsUpdateResponse(readingLists);
    }
}
