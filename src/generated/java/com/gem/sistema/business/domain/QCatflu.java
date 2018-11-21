package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatflu is a Querydsl query type for Catflu
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatflu extends EntityPathBase<Catflu> {

    private static final long serialVersionUID = 2003889933L;

    public static final QCatflu catflu = new QCatflu("catflu");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Double> clvflu = createNumber("clvflu", Double.class);

    public final StringPath gruflu = createString("gruflu");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> mesflu1 = createNumber("mesflu1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu10 = createNumber("mesflu10", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu11 = createNumber("mesflu11", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu12 = createNumber("mesflu12", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu13 = createNumber("mesflu13", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu14 = createNumber("mesflu14", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu15 = createNumber("mesflu15", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu16 = createNumber("mesflu16", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu2 = createNumber("mesflu2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu3 = createNumber("mesflu3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu4 = createNumber("mesflu4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu5 = createNumber("mesflu5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu6 = createNumber("mesflu6", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu7 = createNumber("mesflu7", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu8 = createNumber("mesflu8", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mesflu9 = createNumber("mesflu9", java.math.BigDecimal.class);

    public final StringPath nomflu = createString("nomflu");

    public final StringPath sguflu = createString("sguflu");

    public final StringPath ssgflu = createString("ssgflu");

    public final StringPath userid = createString("userid");

    public QCatflu(String variable) {
        super(Catflu.class, forVariable(variable));
    }

    public QCatflu(Path<? extends Catflu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatflu(PathMetadata<?> metadata) {
        super(Catflu.class, metadata);
    }

}

