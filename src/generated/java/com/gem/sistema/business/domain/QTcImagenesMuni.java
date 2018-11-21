package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcImagenesMuni is a Querydsl query type for TcImagenesMuni
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcImagenesMuni extends EntityPathBase<TcImagenesMuni> {

    private static final long serialVersionUID = -446224985L;

    public static final QTcImagenesMuni tcImagenesMuni = new QTcImagenesMuni("tcImagenesMuni");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> clvmun = createNumber("clvmun", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombreFile = createString("nombreFile");

    public final StringPath pathFile = createString("pathFile");

    public QTcImagenesMuni(String variable) {
        super(TcImagenesMuni.class, forVariable(variable));
    }

    public QTcImagenesMuni(Path<? extends TcImagenesMuni> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcImagenesMuni(PathMetadata<?> metadata) {
        super(TcImagenesMuni.class, metadata);
    }

}

