package edu.swarthmore.cs.cs71.shelved.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibList;
import edu.swarthmore.cs.cs71.shelved.model.server.HibListService;
import edu.swarthmore.cs.cs71.shelved.model.simple.ReadingList;
import edu.swarthmore.cs.cs71.shelved.network.InvalidListAddedResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import edu.swarthmore.cs.cs71.shelved.network.ValidListAddedResponse;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

public class ServerRouteAddList extends ServerRoute {

    public ServerRouteAddList(SessionFactory sf) { super(sf); }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        try {

            String listName = request.queryParams("listName");
            String publicStatus = request.queryParams("publicStatus");
            boolean publicStatusBool;
            if (publicStatus.equals("Yes")) {
                publicStatusBool = true;
            } else {
                publicStatusBool = false;
            }

            HibList newList = new HibListService().createList(listName, publicStatusBool);

            ReadingList readingList = new ReadingList(newList.getName(), newList.isPublicStatus());

            return new ValidListAddedResponse(readingList);
            //ReadingList readingList = new ReadingList("myList", true);
            //return new ValidListAddedResponse(readingList);

        } catch (Exception e) {
            return new InvalidListAddedResponse("Invalid list added response");
        }
    }
}
