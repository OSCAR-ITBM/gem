package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatben is a Querydsl query type for Catben
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatben extends EntityPathBase<Catben> {

    private static final long serialVersionUID = 2003885865L;

    public static final QCatben catben = new QCatben("catben");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> clvben = createNumber("clvben", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> corpla = createNumber("corpla", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<java.math.BigDecimal> larpla = createNumber("larpla", java.math.BigDecimal.class);

    public final StringPath nomben = createString("nomben");

    public QCatben(String variable) {
        super(Catben.class, forVariable(variable));
    }

    public QCatben(Path<? extends Catben> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatben(PathMetadata<?> metadata) {
        super(Catben.class, metadata);
    }

}

