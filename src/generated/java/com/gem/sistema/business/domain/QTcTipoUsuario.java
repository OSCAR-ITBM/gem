package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcTipoUsuario is a Querydsl query type for TcTipoUsuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcTipoUsuario extends EntityPathBase<TcTipoUsuario> {

    private static final long serialVersionUID = 760768183L;

    public static final QTcTipoUsuario tcTipoUsuario = new QTcTipoUsuario("tcTipoUsuario");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QTcTipoUsuario(String variable) {
        super(TcTipoUsuario.class, forVariable(variable));
    }

    public QTcTipoUsuario(Path<? extends TcTipoUsuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcTipoUsuario(PathMetadata<?> metadata) {
        super(TcTipoUsuario.class, metadata);
    }

}

