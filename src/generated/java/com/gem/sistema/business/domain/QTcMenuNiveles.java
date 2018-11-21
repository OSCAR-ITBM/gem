package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcMenuNiveles is a Querydsl query type for TcMenuNiveles
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcMenuNiveles extends EntityPathBase<TcMenuNiveles> {

    private static final long serialVersionUID = 224635694L;

    public static final QTcMenuNiveles tcMenuNiveles = new QTcMenuNiveles("tcMenuNiveles");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> claveMenu = createNumber("claveMenu", Integer.class);

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idMenuPadre = createNumber("idMenuPadre", Integer.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath label = createString("label");

    public final NumberPath<Integer> posicion = createNumber("posicion", Integer.class);

    public final StringPath styleclass = createString("styleclass");

    public final StringPath url = createString("url");

    public QTcMenuNiveles(String variable) {
        super(TcMenuNiveles.class, forVariable(variable));
    }

    public QTcMenuNiveles(Path<? extends TcMenuNiveles> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcMenuNiveles(PathMetadata<?> metadata) {
        super(TcMenuNiveles.class, metadata);
    }

}

