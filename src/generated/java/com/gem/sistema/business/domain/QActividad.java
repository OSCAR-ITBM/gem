package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QActividad is a Querydsl query type for Actividad
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QActividad extends EntityPathBase<Actividad> {

    private static final long serialVersionUID = 740006281L;

    public static final QActividad actividad = new QActividad("actividad");

    public final StringPath clvdepg = createString("clvdepg");

    public final NumberPath<Integer> cveact = createNumber("cveact", Integer.class);

    public final NumberPath<Integer> cvecom = createNumber("cvecom", Integer.class);

    public final StringPath cvefin = createString("cvefin");

    public final StringPath cveind = createString("cveind");

    public final StringPath cveprog = createString("cveprog");

    public final StringPath cvetemas = createString("cvetemas");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final StringPath formula = createString("formula");

    public final StringPath frecuencia = createString("frecuencia");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath medio = createString("medio");

    public final StringPath nombre = createString("nombre");

    public final StringPath objetivo = createString("objetivo");

    public final NumberPath<Integer> sectorid = createNumber("sectorid", Integer.class);

    public final StringPath supuesto = createString("supuesto");

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public QActividad(String variable) {
        super(Actividad.class, forVariable(variable));
    }

    public QActividad(Path<? extends Actividad> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActividad(PathMetadata<?> metadata) {
        super(Actividad.class, metadata);
    }

}

