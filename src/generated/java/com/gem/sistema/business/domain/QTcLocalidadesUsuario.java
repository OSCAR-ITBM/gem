package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcLocalidadesUsuario is a Querydsl query type for TcLocalidadesUsuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcLocalidadesUsuario extends EntityPathBase<TcLocalidadesUsuario> {

    private static final long serialVersionUID = -1086115750L;

    public static final QTcLocalidadesUsuario tcLocalidadesUsuario = new QTcLocalidadesUsuario("tcLocalidadesUsuario");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QTcLocalidadesUsuario(String variable) {
        super(TcLocalidadesUsuario.class, forVariable(variable));
    }

    public QTcLocalidadesUsuario(Path<? extends TcLocalidadesUsuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcLocalidadesUsuario(PathMetadata<?> metadata) {
        super(TcLocalidadesUsuario.class, metadata);
    }

}

