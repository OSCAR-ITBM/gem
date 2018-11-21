package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIngreso is a Querydsl query type for Ingreso
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIngreso extends EntityPathBase<Ingreso> {

    private static final long serialVersionUID = -913342723L;

    public static final QIngreso ingreso = new QIngreso("ingreso");

    public final QMonthlyAbstractEntity _super = new QMonthlyAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> autoi1 = createNumber("autoi1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi10 = createNumber("autoi10", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi11 = createNumber("autoi11", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi12 = createNumber("autoi12", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi13 = createNumber("autoi13", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi2 = createNumber("autoi2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi3 = createNumber("autoi3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi4 = createNumber("autoi4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi5 = createNumber("autoi5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi6 = createNumber("autoi6", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi7 = createNumber("autoi7", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi8 = createNumber("autoi8", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi9 = createNumber("autoi9", java.math.BigDecimal.class);

    public final ListPath<java.math.BigDecimal, NumberPath<java.math.BigDecimal>> autoiAsList = this.<java.math.BigDecimal, NumberPath<java.math.BigDecimal>>createList("autoiAsList", java.math.BigDecimal.class, NumberPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> campo1 = createNumber("campo1", Integer.class);

    public final StringPath clvfuen = createString("clvfuen");

    public final StringPath cuenta = createString("cuenta");

    public final StringPath cvetxt = createString("cvetxt");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idsector = createNumber("idsector", Long.class);

    public final StringPath scta = createString("scta");

    public final StringPath sscta = createString("sscta");

    public final StringPath ssscta = createString("ssscta");

    public final StringPath sssscta = createString("sssscta");

    //inherited
    public final NumberPath<java.math.BigDecimal> suma = _super.suma;

    public QIngreso(String variable) {
        super(Ingreso.class, forVariable(variable));
    }

    public QIngreso(Path<? extends Ingreso> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngreso(PathMetadata<?> metadata) {
        super(Ingreso.class, metadata);
    }

}

