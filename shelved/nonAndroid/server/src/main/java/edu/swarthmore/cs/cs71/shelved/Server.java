package edu.swarthmore.cs.cs71.shelved;

import spark.Service;

public class Server {

    public static void main(String[] args) {
        Service service = Service.ignite();
        service.port(34567);
        service.staticFiles.location("/");
        service.init();

    }
}
