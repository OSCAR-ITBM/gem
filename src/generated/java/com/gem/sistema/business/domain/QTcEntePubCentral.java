package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcEntePubCentral is a Querydsl query type for TcEntePubCentral
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcEntePubCentral extends EntityPathBase<TcEntePubCentral> {

    private static final long serialVersionUID = 21285301L;

    public static final QTcEntePubCentral tcEntePubCentral = new QTcEntePubCentral("tcEntePubCentral");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath desc = createString("desc");

    public final StringPath ente = createString("ente");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QTcEntePubCentral(String variable) {
        super(TcEntePubCentral.class, forVariable(variable));
    }

    public QTcEntePubCentral(Path<? extends TcEntePubCentral> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcEntePubCentral(PathMetadata<?> metadata) {
        super(TcEntePubCentral.class, metadata);
    }

}

