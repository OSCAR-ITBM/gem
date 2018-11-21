package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcRegistraArchivoDigital is a Querydsl query type for TcRegistraArchivoDigital
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcRegistraArchivoDigital extends EntityPathBase<TcRegistraArchivoDigital> {

    private static final long serialVersionUID = -189611596L;

    public static final QTcRegistraArchivoDigital tcRegistraArchivoDigital = new QTcRegistraArchivoDigital("tcRegistraArchivoDigital");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath anio = createString("anio");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath mes = createString("mes");

    public final StringPath nombreArchivo = createString("nombreArchivo");

    public final NumberPath<Integer> numero = createNumber("numero", Integer.class);

    public final StringPath pathFile = createString("pathFile");

    public final NumberPath<Integer> sectorId = createNumber("sectorId", Integer.class);

    public final StringPath tipo = createString("tipo");

    public final StringPath userId = createString("userId");

    public QTcRegistraArchivoDigital(String variable) {
        super(TcRegistraArchivoDigital.class, forVariable(variable));
    }

    public QTcRegistraArchivoDigital(Path<? extends TcRegistraArchivoDigital> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcRegistraArchivoDigital(PathMetadata<?> metadata) {
        super(TcRegistraArchivoDigital.class, metadata);
    }

}

