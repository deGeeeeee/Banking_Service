

package ch.bzz.webBanking.service;
import ch.bzz.webBanking.data.DataHandler;
import ch.bzz.webBanking.model.Client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.util.Map;
import java.util.UUID;

/**
 * provides services for online banking
 * <p>
 * M133: Banking Service
 *
 * @author Leon Funtik
 */
@Path("banking")
public class BankingService {

    /**
     * produces a map of all Clients
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listClients() {
        Map<String, Client> clientMap = DataHandler.getClientMap();
        Response response = Response
                .status(200)
                .entity(clientMap)
                .build();
        return response;

    }

    /**
     * reads a single book identified by the bookId
     *
     * @param clientUUID the clientUUID in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)

    public Response readClient(
            @QueryParam("uuid") String clientUUID
    ) {
        Client client = null;
        int httpStatus;

        try {
            UUID clientKey = UUID.fromString(clientUUID);
            client = DataHandler.readClient(clientUUID);
            if (client.getName() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(client)
                .build();
        return response;
    }
}