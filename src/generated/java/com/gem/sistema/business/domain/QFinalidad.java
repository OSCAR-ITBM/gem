package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFinalidad is a Querydsl query type for Finalidad
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFinalidad extends EntityPathBase<Finalidad> {

    private static final long serialVersionUID = 891272768L;

    public static final QFinalidad finalidad = new QFinalidad("finalidad");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvdepg = createString("clvdepg");

    public final StringPath cvefin = createString("cvefin");

    public final NumberPath<Long> cvefinal = createNumber("cvefinal", Long.class);

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

    public QFinalidad(String variable) {
        super(Finalidad.class, forVariable(variable));
    }

    public QFinalidad(Path<? extends Finalidad> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFinalidad(PathMetadata<?> metadata) {
        super(Finalidad.class, metadata);
    }

}

