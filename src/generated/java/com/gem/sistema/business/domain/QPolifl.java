package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPolifl is a Querydsl query type for Polifl
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPolifl extends EntityPathBase<Polifl> {

    private static final long serialVersionUID = -1906204746L;

    public static final QPolifl polifl = new QPolifl("polifl");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final NumberPath<Integer> campo = createNumber("campo", Integer.class);

    public final NumberPath<java.math.BigDecimal> canflu = createNumber("canflu", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> clvflu = createNumber("clvflu", java.math.BigDecimal.class);

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<java.math.BigDecimal> renpol = createNumber("renpol", java.math.BigDecimal.class);

    public final StringPath tippol = createString("tippol");

    public final StringPath userid = createString("userid");

    public QPolifl(String variable) {
        super(Polifl.class, forVariable(variable));
    }

    public QPolifl(Path<? extends Polifl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolifl(PathMetadata<?> metadata) {
        super(Polifl.class, metadata);
    }

}

