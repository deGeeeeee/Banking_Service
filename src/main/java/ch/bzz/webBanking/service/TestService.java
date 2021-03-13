package ch.bzz.webBanking.service;

import ch.bzz.webBanking.data.DataHandler;
import ch.bzz.webBanking.model.Client;
import ch.bzz.webBanking.model.Account;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * a short test run
 * <p>
 * online Banking
 *
 * @author Leon Funtik
 */
@Path("test")
public class TestService {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("succccess")
                .build();
    }
}