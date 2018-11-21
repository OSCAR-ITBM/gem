package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatprv is a Querydsl query type for Catprv
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatprv extends EntityPathBase<Catprv> {

    private static final long serialVersionUID = 2003899730L;

    public static final QCatprv catprv = new QCatprv("catprv");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capprv = createString("capprv");

    public final NumberPath<Integer> clvprv = createNumber("clvprv", Integer.class);

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath nomprv = createString("nomprv");

    public final StringPath tipprv = createString("tipprv");

    public final StringPath userid = createString("userid");

    public QCatprv(String variable) {
        super(Catprv.class, forVariable(variable));
    }

    public QCatprv(Path<? extends Catprv> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatprv(PathMetadata<?> metadata) {
        super(Catprv.class, metadata);
    }

}

