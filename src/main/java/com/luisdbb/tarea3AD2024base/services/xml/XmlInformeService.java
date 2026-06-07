package com.luisdbb.tarea3AD2024base.services.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.luisdbb.tarea3AD2024base.services.EspectaculoService;

@Service
public class XmlInformeService {

    @Autowired
    private EspectaculoService espectaculoService;
    

    @Autowired
    private ExistDbService existDbService;


    public void generarInformeEspectaculo(
            Long idEspectaculo) {

        try {

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document document =
                    builder.newDocument();

            /* RAIZ */

            Element informe =
                    document.createElement(
                            "informe");

            document.appendChild(
                    informe);
            
	        Element fechaHora = document.createElement("fechahora");
	
	        fechaHora.setTextContent(
	
	        java.time.LocalDateTime.now().toString());
	
	        informe.appendChild(fechaHora);


            /* ESPECTACULO */

            var espectaculo =

                    espectaculoService
                            .obtenerEspectaculoCompleto(
                                    idEspectaculo);

            Element espectaculoElement =

                    document.createElement(
                            "espectaculo");

            informe.appendChild(
                    espectaculoElement);

            /* ID */

            Element id =
                    document.createElement(
                            "id");

            id.setTextContent(
                    String.valueOf(
                            espectaculo.getId()));

            espectaculoElement.appendChild(
                    id);

            /* NOMBRE */

            Element nombre =
                    document.createElement(
                            "nombre");

            nombre.setTextContent(
                    espectaculo.getNombre());

            espectaculoElement.appendChild(
                    nombre);

            /* FECHA INICIO */

            Element fechaInicio =
                    document.createElement(
                            "fechaini");

            fechaInicio.setTextContent(

                    espectaculo
                            .getFechaInicio()
                            .toString());

            espectaculoElement.appendChild(
                    fechaInicio);

            /* FECHA FIN */

            Element fechaFin =
                    document.createElement(
                            "fechafin");

            fechaFin.setTextContent(

                    espectaculo
                            .getFechaFin()
                            .toString());

            espectaculoElement.appendChild(
                    fechaFin);

            /* COORDINACION */

            Element coordinacion =

                    document.createElement(
                            "coordinacion");

            espectaculoElement.appendChild(
                    coordinacion);

            /* NOMBRE COORD */

            Element nombreCoord =
                    document.createElement(
                            "nombre");

            nombreCoord.setTextContent(

                    espectaculo
                            .getCoordinador()
                            .getNombre());

            coordinacion.appendChild(
                    nombreCoord);

            /* EMAIL COORD */

            Element emailCoord =
                    document.createElement(
                            "email");

            emailCoord.setTextContent(

                    espectaculo
                            .getCoordinador()
                            .getEmail());

            coordinacion.appendChild(
                    emailCoord);

            /* SENIOR */

            Element senior =
                    document.createElement(
                            "senior");

            senior.setTextContent(

                    String.valueOf(

                            espectaculo
                                    .getCoordinador()
                                    .isSenior()));

            coordinacion.appendChild(
                    senior);

            /* NUMEROS */

            Element numeros =

                    document.createElement(
                            "numeros");

            espectaculoElement.appendChild(
                    numeros);

            for (var numero : espectaculo.getNumeros()) {

                Element numeroElement =

                        document.createElement(
                                "numero");

                numeros.appendChild(
                        numeroElement);

                /* ORDEN */

                Element orden =
                        document.createElement(
                                "orden");

                orden.setTextContent(

                        String.valueOf(
                                numero.getOrden()));

                numeroElement.appendChild(
                        orden);

                /* NOMBRE NUMERO */

                Element nombreNumero =
                        document.createElement(
                                "nombre");

                nombreNumero.setTextContent(
                        numero.getNombre());

                numeroElement.appendChild(
                        nombreNumero);

                /* DURACION */

                Element duracion =
                        document.createElement(
                                "duracion");

                duracion.setTextContent(

                        String.valueOf(
                                numero.getDuracion()));

                numeroElement.appendChild(
                        duracion);

                /* ARTISTAS */

                Element artistas =

                        document.createElement(
                                "artistas");

                numeroElement.appendChild(
                        artistas);

                for (var artista : numero.getArtistas()) {

                    Element artistaElement =

                            document.createElement(
                                    "artista");

                    artistas.appendChild(
                            artistaElement);

                    /* NOMBRE */

                    Element nombreArtista =
                            document.createElement(
                                    "nombre");

                    nombreArtista.setTextContent(
                            artista.getNombre());

                    artistaElement.appendChild(
                            nombreArtista);

                    /* NACIONALIDAD */

                    Element nacionalidad =
                            document.createElement(
                                    "nacionalidad");

                    nacionalidad.setTextContent(

                            artista.getNacionalidad()
                                    .toString());

                    artistaElement.appendChild(
                            nacionalidad);

                    /* EMAIL */

                    Element emailArtista =
                            document.createElement(
                                    "email");

                    emailArtista.setTextContent(
                            artista.getEmail());

                    artistaElement.appendChild(
                            emailArtista);

                    /* ESPECIALIDADES */

                    Element especialidades =
                            document.createElement(
                                    "especialidades");

                    especialidades.setTextContent(

                            artista.getEspecialidades()
                                    .toString());

                    artistaElement.appendChild(
                            especialidades);

                    /* APODO */

                    if (artista.getApodo() != null
                            && !artista.getApodo().isBlank()) {

                        Element apodo =
                                document.createElement(
                                        "apodo");

                        apodo.setTextContent(
                                artista.getApodo());

                        artistaElement.appendChild(
                                apodo);
                    }
                }
            }

            /* CARPETA */

            File carpeta =
                    new File("ficheros");

            if (!carpeta.exists()) {

                carpeta.mkdirs();
            }

            /* ARCHIVO */

            File archivo = new File("ficheros/informe_espectaculo" + espectaculo.getId() + ".xml");

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();

            Transformer transformer =
                    transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            
            transformer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source =
                    new DOMSource(document);

            StreamResult result =
                    new StreamResult(archivo);

            transformer.transform(
                    source,
                    result);
            
            existDbService.guardarXML( archivo);

            System.out.println(
                    "XML generado correctamente");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
