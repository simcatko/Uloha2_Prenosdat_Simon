package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.IotNode;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@SwaggerDefinition(
        info = @Info(
                description = "IotNode data",
                version = "V1.0.0",
                title = "IotNode service",
                contact = @Contact(
                        name = "Simona Prokopova",
                        email = "sima@non-existent.sk"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP}

)
@Api("/IotNode")
@Path("/IotNode")
@Produces(MediaType.APPLICATION_JSON)// Výstupné dáta sú vo forme JSON //JAX-RS
@Consumes(MediaType.APPLICATION_JSON) //Vstupné dáta sú vo forme JSON //JAX-RS
public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Pridanie noveho IoT vrcholu")
    public IotNode createIotNode(@Valid IotNode iotNode) {
        return iotNodeDAO.create(iotNode);
    }

    @PUT /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Uprava IoT vrcholu")
    public IotNode updateIotNode(@Valid IotNode iotNode) {
        return iotNodeDAO.update(iotNode);
    }

    @GET //HTTP metóda
    @Path("{id}") // Jedna vetva hlavnej adresy /household
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Údaje o konkrétnom IoT vrchole")
    public IotNode findIotNode(@ApiParam(value = "ID", required = true) @PathParam("id") Long id) {
        return iotNodeDAO.findById(id);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zoznam vsetky IoT vrcholov")
    public List<IotNode> allIotNodes() {
        return iotNodeDAO.allIotNodes();
    }

}
