package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcMenuItem is a Querydsl query type for TcMenuItem
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcMenuItem extends EntityPathBase<TcMenuItem> {

    private static final long serialVersionUID = -992309035L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTcMenuItem tcMenuItem = new QTcMenuItem("tcMenuItem");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath label = createString("label");

    public final QTcMenu menu;

    public final StringPath styleclass = createString("styleclass");

    public final StringPath url = createString("url");

    public QTcMenuItem(String variable) {
        this(TcMenuItem.class, forVariable(variable), INITS);
    }

    public QTcMenuItem(Path<? extends TcMenuItem> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTcMenuItem(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTcMenuItem(PathMetadata<?> metadata, PathInits inits) {
        this(TcMenuItem.class, metadata, inits);
    }

    public QTcMenuItem(Class<? extends TcMenuItem> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.menu = inits.isInitialized("menu") ? new QTcMenu(forProperty("menu")) : null;
    }

}

