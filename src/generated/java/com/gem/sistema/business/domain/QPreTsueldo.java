package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPreTsueldo is a Querydsl query type for PreTsueldo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPreTsueldo extends EntityPathBase<PreTsueldo> {

    private static final long serialVersionUID = -434214311L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPreTsueldo preTsueldo = new QPreTsueldo("preTsueldo");

    public final QPreTsueldoPK id;

    public QPreTsueldo(String variable) {
        this(PreTsueldo.class, forVariable(variable), INITS);
    }

    public QPreTsueldo(Path<? extends PreTsueldo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPreTsueldo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPreTsueldo(PathMetadata<?> metadata, PathInits inits) {
        this(PreTsueldo.class, metadata, inits);
    }

    public QPreTsueldo(Class<? extends PreTsueldo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QPreTsueldoPK(forProperty("id")) : null;
    }

}

