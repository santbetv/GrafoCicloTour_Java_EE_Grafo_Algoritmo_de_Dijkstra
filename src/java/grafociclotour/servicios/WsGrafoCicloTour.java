/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafociclotour.servicios;

/**
 *
 * @author Santiago Betancur Villegas
 */
import grafociclotour.controlador.ControladorGrafo;
import grafociclotour.controlador.Dijkstra;
import grafociclotour.modelo.Arista;
import grafociclotour.modelo.Municipio;
import grafociclotour.modelo.Vertice;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * @author Santiago Betancur
 */
@WebService(serviceName = "WsGrafoCicloTour")
public class WsGrafoCicloTour {

    private ControladorGrafo controlGrafo;

    @WebMethod(operationName = "iniciarGrafo")
    public String iniciarGrafo() {

        controlGrafo = new ControladorGrafo();
        controlGrafo.inicializar();

        return "Grafo Iniciado";
    }

    @WebMethod(operationName = "listarVertices")
    public List<Vertice> listarVertices() {
        return controlGrafo.getGrafoND().getVertices();
    }

    @WebMethod(operationName = "crearVertice")
    public String crearVertice(@WebParam(name = "ciudad") Municipio ciudad) {
        controlGrafo.getGrafoND().adicionarVertice(
                new Vertice(controlGrafo.getGrafoND().getVertices().size() + 1,
                        ciudad));
        return "Vertice creado";
    }

    @WebMethod(operationName = "borrarVertice")
    public String borrarVertice(@WebParam(name = "borarVerice") int codigo) {
        for (int i = 0; i < controlGrafo.getGrafoND().getVertices().size(); i++) {
            if (controlGrafo.getGrafoND().getVertices().get(i).getCodigo() == codigo) {
                controlGrafo.getGrafoND().getVertices().remove(i);
            }
        }
        return "Vertice Borrado";
    }

    @WebMethod(operationName = "crearArista")
    public String crearArista(@WebParam(name = "origen") int origen, @WebParam(name = "destino") int destino, @WebParam(name = "peso") int peso) {

        if (controlGrafo.getGrafoND().verificarOrigenVerticeWs(origen)) {
            if (controlGrafo.getGrafoND().verificarDestinoVerticeWs(destino)) {
                if (controlGrafo.getGrafoND().verificarAristaWs(origen, destino)) {
                    return "Ya existe comunicación entre los dos vertices";
                } else {
                    controlGrafo.getGrafoND().adicionarArista(new Arista(origen, destino, peso));
                }
            } else {
                return "Destino NO existe";
            }
        } else {
            return "Origen NO existe";
        }
        return "Arista Creada";
    }

    //Buscar por codigo
    @WebMethod(operationName = "obtenerRutaMasCortaXCodigo")
    public List<Vertice> obtenerRutaMasCortaXCodigo(@WebParam(name = "inicio") int inicio, @WebParam(name = "destino") int destino) {
        List<Vertice> ciudades = new ArrayList<>();
        if (destino != inicio) {
            Dijkstra dijstra = new Dijkstra(controlGrafo.getGrafoND(), controlGrafo.getGrafoND().obtenerVerticexCodigo(inicio), controlGrafo.getGrafoND().obtenerVerticexCodigo(destino));
            ciudades = dijstra.calcularRutaMasCorta();
        } else {

        }
        return ciudades;
    }

}
