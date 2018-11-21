package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCattpo is a Querydsl query type for Cattpo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCattpo extends EntityPathBase<Cattpo> {

    private static final long serialVersionUID = 2003903505L;

    public static final QCattpo cattpo = new QCattpo("cattpo");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath tipnom = createString("tipnom");

    public final StringPath tippol = createString("tippol");

    public QCattpo(String variable) {
        super(Cattpo.class, forVariable(variable));
    }

    public QCattpo(Path<? extends Cattpo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCattpo(PathMetadata<?> metadata) {
        super(Cattpo.class, metadata);
    }

}

