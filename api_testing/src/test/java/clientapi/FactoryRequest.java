package clientapi;

public class FactoryRequest {
    public static IRequest make(String httpVerb){
        IRequest request;

        switch (httpVerb.toUpperCase()){
            case "POST":
                request = new Post();
                break;
            case "PUT":
                request = new Put();
                break;
            case "DELETE":
                request = new Delete();
                break;
            case "GET":
            default:
                request = new Get();
        }
        return request;
    }
}
