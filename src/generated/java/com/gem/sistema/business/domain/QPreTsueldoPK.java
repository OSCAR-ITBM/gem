package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPreTsueldoPK is a Querydsl query type for PreTsueldoPK
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QPreTsueldoPK extends BeanPath<PreTsueldoPK> {

    private static final long serialVersionUID = -668122604L;

    public static final QPreTsueldoPK preTsueldoPK = new QPreTsueldoPK("preTsueldoPK");

    public final StringPath cvepuesto = createString("cvepuesto");

    public final StringPath nompuesto = createString("nompuesto");

    public QPreTsueldoPK(String variable) {
        super(PreTsueldoPK.class, forVariable(variable));
    }

    public QPreTsueldoPK(Path<? extends PreTsueldoPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreTsueldoPK(PathMetadata<?> metadata) {
        super(PreTsueldoPK.class, metadata);
    }

}

