package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatmun is a Querydsl query type for Catmun
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatmun extends EntityPathBase<Catmun> {

    private static final long serialVersionUID = 2003896932L;

    public static final QCatmun catmun = new QCatmun("catmun");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> a311 = createNumber("a311", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> a323 = createNumber("a323", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> c311 = createNumber("c311", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> c323 = createNumber("c323", java.math.BigDecimal.class);

    public final NumberPath<Integer> clvmun = createNumber("clvmun", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nommun = createString("nommun");

    public QCatmun(String variable) {
        super(Catmun.class, forVariable(variable));
    }

    public QCatmun(Path<? extends Catmun> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatmun(PathMetadata<?> metadata) {
        super(Catmun.class, metadata);
    }

}

