package com.luisdbb.tarea3AD2024base.services.existdb;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.BinaryResource;
import org.xmldb.api.modules.CollectionManagementService;

@Service
public class ExistDbService {

    @Value("${existdb.uri}")
    private String uri;

    @Value("${existdb.user}")
    private String user;

    @Value("${existdb.password}")
    private String password;

    @Value("${existdb.collection}")
    private String collectionPath;

    public void guardarXML(
            File archivo) {

        try {
            
            org.xmldb.api.base.Database database =

                    (org.xmldb.api.base.Database)

                            Class.forName(
                                    "org.exist.xmldb.DatabaseImpl")
                                    .getDeclaredConstructor()
                                    .newInstance();

            DatabaseManager.registerDatabase(
                    database);

            Collection collection =

                    DatabaseManager.getCollection(
                            uri + collectionPath,
                            user,
                            password);

            if (collection == null) {

                Collection root =

                        DatabaseManager.getCollection(
                                uri + "/db",
                                user,
                                password);

                CollectionManagementService service =

                        (CollectionManagementService)

                                root.getService(
                                        "CollectionManagementService",
                                        "1.0");

                service.createCollection(
                        "informes");

                collection =

                        DatabaseManager.getCollection(
                                uri + collectionPath,
                                user,
                                password);
            }

            BinaryResource resource =

                    (BinaryResource)

                            collection.createResource(
                                    archivo.getName(),
                                    "BinaryResource");

            resource.setContent(archivo);

            collection.storeResource(
                    resource);

            collection.close();

            System.out.println(
                    "XML guardado en ExistDB");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
