package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatdgm is a Querydsl query type for Catdgm
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatdgm extends EntityPathBase<Catdgm> {

    private static final long serialVersionUID = 2003887848L;

    public static final QCatdgm catdgm = new QCatdgm("catdgm");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    public final DateTimePath<java.util.Date> fecha = createDateTime("fecha", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public QCatdgm(String variable) {
        super(Catdgm.class, forVariable(variable));
    }

    public QCatdgm(Path<? extends Catdgm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatdgm(PathMetadata<?> metadata) {
        super(Catdgm.class, metadata);
    }

}

