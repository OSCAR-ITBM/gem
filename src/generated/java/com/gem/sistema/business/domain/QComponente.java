package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QComponente is a Querydsl query type for Componente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QComponente extends EntityPathBase<Componente> {

    private static final long serialVersionUID = -1030208708L;

    public static final QComponente componente = new QComponente("componente");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvdepg = createString("clvdepg");

    public final NumberPath<Integer> cvecom = createNumber("cvecom", Integer.class);

    public final StringPath cvefin = createString("cvefin");

    public final StringPath cveind = createString("cveind");

    public final StringPath cveprog = createString("cveprog");

    public final StringPath cvetemas = createString("cvetemas");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final StringPath formula = createString("formula");

    public final StringPath frecuencia = createString("frecuencia");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath medio = createString("medio");

    public final StringPath nombre = createString("nombre");

    public final StringPath objetivo = createString("objetivo");

    public final NumberPath<Integer> sectorid = createNumber("sectorid", Integer.class);

    public final StringPath supuesto = createString("supuesto");

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public QComponente(String variable) {
        super(Componente.class, forVariable(variable));
    }

    public QComponente(Path<? extends Componente> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComponente(PathMetadata<?> metadata) {
        super(Componente.class, metadata);
    }

}

