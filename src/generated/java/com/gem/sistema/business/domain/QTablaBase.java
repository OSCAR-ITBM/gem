package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTablaBase is a Querydsl query type for TablaBase
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QTablaBase extends EntityPathBase<TablaBase> {

    private static final long serialVersionUID = -1457378553L;

    public static final QTablaBase tablaBase = new QTablaBase("tablaBase");

    public final StringPath capgas = createString("capgas");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final StringPath indcap = createString("indcap");

    public QTablaBase(String variable) {
        super(TablaBase.class, forVariable(variable));
    }

    public QTablaBase(Path<? extends TablaBase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTablaBase(PathMetadata<?> metadata) {
        super(TablaBase.class, metadata);
    }

}

