package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcEntidadAdministrativa is a Querydsl query type for TcEntidadAdministrativa
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcEntidadAdministrativa extends EntityPathBase<TcEntidadAdministrativa> {

    private static final long serialVersionUID = -984302448L;

    public static final QTcEntidadAdministrativa tcEntidadAdministrativa = new QTcEntidadAdministrativa("tcEntidadAdministrativa");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QTcEntidadAdministrativa(String variable) {
        super(TcEntidadAdministrativa.class, forVariable(variable));
    }

    public QTcEntidadAdministrativa(Path<? extends TcEntidadAdministrativa> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcEntidadAdministrativa(PathMetadata<?> metadata) {
        super(TcEntidadAdministrativa.class, metadata);
    }

}

