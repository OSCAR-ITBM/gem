package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTipcta is a Querydsl query type for Tipcta
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTipcta extends EntityPathBase<Tipcta> {

    private static final long serialVersionUID = -1797115447L;

    public static final QTipcta tipcta = new QTipcta("tipcta");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvsti = createString("clvsti");

    public final StringPath clvtip = createString("clvtip");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nomtip = createString("nomtip");

    public QTipcta(String variable) {
        super(Tipcta.class, forVariable(variable));
    }

    public QTipcta(Path<? extends Tipcta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipcta(PathMetadata<?> metadata) {
        super(Tipcta.class, metadata);
    }

}

