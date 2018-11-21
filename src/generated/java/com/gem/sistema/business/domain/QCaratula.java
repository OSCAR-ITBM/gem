package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCaratula is a Querydsl query type for Caratula
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCaratula extends EntityPathBase<Caratula> {

    private static final long serialVersionUID = 1531242871L;

    public static final QCaratula caratula = new QCaratula("caratula");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> auto = createNumber("auto", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi1 = createNumber("autoi1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi2 = createNumber("autoi2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi3 = createNumber("autoi3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi4 = createNumber("autoi4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> autoi5 = createNumber("autoi5", java.math.BigDecimal.class);

    public final StringPath cuenta = createString("cuenta");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath nombre = createString("nombre");

    public final StringPath scta = createString("scta");

    public final NumberPath<Integer> sectorid = createNumber("sectorid", Integer.class);

    public final StringPath sscta = createString("sscta");

    public final StringPath ssscta = createString("ssscta");

    public final StringPath sssscta = createString("sssscta");

    public final StringPath userid = createString("userid");

    public QCaratula(String variable) {
        super(Caratula.class, forVariable(variable));
    }

    public QCaratula(Path<? extends Caratula> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCaratula(PathMetadata<?> metadata) {
        super(Caratula.class, metadata);
    }

}

