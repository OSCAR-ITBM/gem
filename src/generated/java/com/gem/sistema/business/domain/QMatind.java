package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMatind is a Querydsl query type for Matind
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMatind extends EntityPathBase<Matind> {

    private static final long serialVersionUID = -2004782925L;

    public static final QMatind matind = new QMatind("matind");

    public final StringPath clvdepg = createString("clvdepg");

    public final NumberPath<Integer> componente = createNumber("componente", Integer.class);

    public final StringPath cvefin = createString("cvefin");

    public final StringPath cveprog = createString("cveprog");

    public final StringPath cvetemas = createString("cvetemas");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Integer> finalidad = createNumber("finalidad", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath objetivo = createString("objetivo");

    public final NumberPath<Integer> proposito = createNumber("proposito", Integer.class);

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public QMatind(String variable) {
        super(Matind.class, forVariable(variable));
    }

    public QMatind(Path<? extends Matind> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMatind(PathMetadata<?> metadata) {
        super(Matind.class, metadata);
    }

}

