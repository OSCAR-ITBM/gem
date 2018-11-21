package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QNatgas is a Querydsl query type for Natgas
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNatgas extends EntityPathBase<Natgas> {

    private static final long serialVersionUID = -1976156084L;

    public static final QNatgas natgas = new QNatgas("natgas");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capgas = createString("capgas");

    public final StringPath clvgas = createString("clvgas");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath indcap = createString("indcap");

    public final StringPath nomgas = createString("nomgas");

    public final StringPath userid = createString("userid");

    public QNatgas(String variable) {
        super(Natgas.class, forVariable(variable));
    }

    public QNatgas(Path<? extends Natgas> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNatgas(PathMetadata<?> metadata) {
        super(Natgas.class, metadata);
    }

}

