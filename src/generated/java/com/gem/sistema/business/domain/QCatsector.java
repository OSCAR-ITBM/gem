package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatsector is a Querydsl query type for Catsector
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatsector extends EntityPathBase<Catsector> {

    private static final long serialVersionUID = -1795127288L;

    public static final QCatsector catsector = new QCatsector("catsector");

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath nomsector = createString("nomsector");

    public final StringPath userid = createString("userid");

    public QCatsector(String variable) {
        super(Catsector.class, forVariable(variable));
    }

    public QCatsector(Path<? extends Catsector> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatsector(PathMetadata<?> metadata) {
        super(Catsector.class, metadata);
    }

}

