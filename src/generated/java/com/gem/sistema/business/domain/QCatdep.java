package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatdep is a Querydsl query type for Catdep
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatdep extends EntityPathBase<Catdep> {

    private static final long serialVersionUID = 2003887789L;

    public static final QCatdep catdep = new QCatdep("catdep");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capdep = createString("capdep");

    public final StringPath cargoE = createString("cargoE");

    public final StringPath cargoT = createString("cargoT");

    public final StringPath clvdep = createString("clvdep");

    public final StringPath elaboro = createString("elaboro");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath lautorizo = createString("lautorizo");

    public final StringPath lelaboro = createString("lelaboro");

    public final StringPath lreviso = createString("lreviso");

    public final StringPath ltitulo1 = createString("ltitulo1");

    public final StringPath ltitulo2 = createString("ltitulo2");

    public final StringPath nautorizo = createString("nautorizo");

    public final StringPath nelaboro = createString("nelaboro");

    public final StringPath nomdep = createString("nomdep");

    public final StringPath nreviso = createString("nreviso");

    public final StringPath ntitulo1 = createString("ntitulo1");

    public final StringPath ntitulo2 = createString("ntitulo2");

    public final StringPath titular = createString("titular");

    public final StringPath ultniv = createString("ultniv");

    public final StringPath userid = createString("userid");

    public QCatdep(String variable) {
        super(Catdep.class, forVariable(variable));
    }

    public QCatdep(Path<? extends Catdep> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatdep(PathMetadata<?> metadata) {
        super(Catdep.class, metadata);
    }

}

