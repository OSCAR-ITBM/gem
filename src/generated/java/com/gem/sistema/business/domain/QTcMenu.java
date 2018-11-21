package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcMenu is a Querydsl query type for TcMenu
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcMenu extends EntityPathBase<TcMenu> {

    private static final long serialVersionUID = -1803697502L;

    public static final QTcMenu tcMenu = new QTcMenu("tcMenu");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath label = createString("label");

    public final ListPath<TcMenuItem, QTcMenuItem> menuItems = this.<TcMenuItem, QTcMenuItem>createList("menuItems", TcMenuItem.class, QTcMenuItem.class, PathInits.DIRECT2);

    public final StringPath styleclass = createString("styleclass");

    public final StringPath url = createString("url");

    public QTcMenu(String variable) {
        super(TcMenu.class, forVariable(variable));
    }

    public QTcMenu(Path<? extends TcMenu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcMenu(PathMetadata<?> metadata) {
        super(TcMenu.class, metadata);
    }

}

