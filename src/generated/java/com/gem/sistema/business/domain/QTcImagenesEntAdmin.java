package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcImagenesEntAdmin is a Querydsl query type for TcImagenesEntAdmin
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcImagenesEntAdmin extends EntityPathBase<TcImagenesEntAdmin> {

    private static final long serialVersionUID = 1130461032L;

    public static final QTcImagenesEntAdmin tcImagenesEntAdmin = new QTcImagenesEntAdmin("tcImagenesEntAdmin");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> clvmun = createNumber("clvmun", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idEntidadAdmin = createNumber("idEntidadAdmin", Long.class);

    public final StringPath nombreFile = createString("nombreFile");

    public final StringPath pathFile = createString("pathFile");

    public QTcImagenesEntAdmin(String variable) {
        super(TcImagenesEntAdmin.class, forVariable(variable));
    }

    public QTcImagenesEntAdmin(Path<? extends TcImagenesEntAdmin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcImagenesEntAdmin(PathMetadata<?> metadata) {
        super(TcImagenesEntAdmin.class, metadata);
    }

}

