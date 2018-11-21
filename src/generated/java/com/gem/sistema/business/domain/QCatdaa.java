package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatdaa is a Querydsl query type for Catdaa
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatdaa extends EntityPathBase<Catdaa> {

    private static final long serialVersionUID = 2003887650L;

    public static final QCatdaa catdaa = new QCatdaa("catdaa");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    public final DateTimePath<java.util.Date> fecha = createDateTime("fecha", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public QCatdaa(String variable) {
        super(Catdaa.class, forVariable(variable));
    }

    public QCatdaa(Path<? extends Catdaa> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatdaa(PathMetadata<?> metadata) {
        super(Catdaa.class, metadata);
    }

}

