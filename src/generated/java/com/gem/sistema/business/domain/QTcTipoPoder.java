package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcTipoPoder is a Querydsl query type for TcTipoPoder
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcTipoPoder extends EntityPathBase<TcTipoPoder> {

    private static final long serialVersionUID = -473234565L;

    public static final QTcTipoPoder tcTipoPoder = new QTcTipoPoder("tcTipoPoder");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idPoder = createNumber("idPoder", Integer.class);

    public QTcTipoPoder(String variable) {
        super(TcTipoPoder.class, forVariable(variable));
    }

    public QTcTipoPoder(Path<? extends TcTipoPoder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcTipoPoder(PathMetadata<?> metadata) {
        super(TcTipoPoder.class, metadata);
    }

}

