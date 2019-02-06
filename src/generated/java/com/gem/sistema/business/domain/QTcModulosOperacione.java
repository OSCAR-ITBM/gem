package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcModulosOperacione is a Querydsl query type for TcModulosOperacione
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcModulosOperacione extends EntityPathBase<TcModulosOperacione> {

    private static final long serialVersionUID = -82609017L;

    public static final QTcModulosOperacione tcModulosOperacione = new QTcModulosOperacione("tcModulosOperacione");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvProceso = createString("clvProceso");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath nombreProceso = createString("nombreProceso");

    public QTcModulosOperacione(String variable) {
        super(TcModulosOperacione.class, forVariable(variable));
    }

    public QTcModulosOperacione(Path<? extends TcModulosOperacione> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcModulosOperacione(PathMetadata<?> metadata) {
        super(TcModulosOperacione.class, metadata);
    }

}

