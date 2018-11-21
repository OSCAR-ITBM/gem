package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVariables is a Querydsl query type for Variables
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVariables extends EntityPathBase<Variables> {

    private static final long serialVersionUID = 452912835L;

    public static final QVariables variables = new QVariables("variables");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath cvevar = createString("cvevar");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nomvar = createString("nomvar");

    public final StringPath usuario = createString("usuario");

    public QVariables(String variable) {
        super(Variables.class, forVariable(variable));
    }

    public QVariables(Path<? extends Variables> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVariables(PathMetadata<?> metadata) {
        super(Variables.class, metadata);
    }

}

