package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcMenus is a Querydsl query type for TcMenus
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcMenus extends EntityPathBase<TcMenus> {

    private static final long serialVersionUID = -80047599L;

    public static final QTcMenus tcMenus = new QTcMenus("tcMenus");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idMenuPadre = createNumber("idMenuPadre", Long.class);

    public final StringPath nombre = createString("nombre");

    public final StringPath styleclass = createString("styleclass");

    public final StringPath url = createString("url");

    public QTcMenus(String variable) {
        super(TcMenus.class, forVariable(variable));
    }

    public QTcMenus(Path<? extends TcMenus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcMenus(PathMetadata<?> metadata) {
        super(TcMenus.class, metadata);
    }

}

