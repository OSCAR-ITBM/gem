package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProposito is a Querydsl query type for Proposito
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProposito extends EntityPathBase<Proposito> {

    private static final long serialVersionUID = -177964023L;

    public static final QProposito proposito = new QProposito("proposito");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvdepg = createString("clvdepg");

    public final StringPath cvefin = createString("cvefin");

    public final StringPath cveind = createString("cveind");

    public final NumberPath<Long> cvepro = createNumber("cvepro", Long.class);

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

    public QProposito(String variable) {
        super(Proposito.class, forVariable(variable));
    }

    public QProposito(Path<? extends Proposito> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProposito(PathMetadata<?> metadata) {
        super(Proposito.class, metadata);
    }

}

