package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTrUsuarios2MenuItems is a Querydsl query type for TrUsuarios2MenuItems
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTrUsuarios2MenuItems extends EntityPathBase<TrUsuarios2MenuItems> {

    private static final long serialVersionUID = -861846362L;

    public static final QTrUsuarios2MenuItems trUsuarios2MenuItems = new QTrUsuarios2MenuItems("trUsuarios2MenuItems");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idMenuItem = createNumber("idMenuItem", Integer.class);

    public final NumberPath<Integer> idUsuario = createNumber("idUsuario", Integer.class);

    public QTrUsuarios2MenuItems(String variable) {
        super(TrUsuarios2MenuItems.class, forVariable(variable));
    }

    public QTrUsuarios2MenuItems(Path<? extends TrUsuarios2MenuItems> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrUsuarios2MenuItems(PathMetadata<?> metadata) {
        super(TrUsuarios2MenuItems.class, metadata);
    }

}

