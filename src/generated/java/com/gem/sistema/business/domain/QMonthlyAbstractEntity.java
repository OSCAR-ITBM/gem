package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMonthlyAbstractEntity is a Querydsl query type for MonthlyAbstractEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QMonthlyAbstractEntity extends EntityPathBase<MonthlyAbstractEntity> {

    private static final long serialVersionUID = -422889634L;

    public static final QMonthlyAbstractEntity monthlyAbstractEntity = new QMonthlyAbstractEntity("monthlyAbstractEntity");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<java.math.BigDecimal> suma = createNumber("suma", java.math.BigDecimal.class);

    public QMonthlyAbstractEntity(String variable) {
        super(MonthlyAbstractEntity.class, forVariable(variable));
    }

    public QMonthlyAbstractEntity(Path<? extends MonthlyAbstractEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonthlyAbstractEntity(PathMetadata<?> metadata) {
        super(MonthlyAbstractEntity.class, metadata);
    }

}

