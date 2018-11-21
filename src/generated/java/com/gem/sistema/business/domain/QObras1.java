package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QObras1 is a Querydsl query type for Obras1
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObras1 extends EntityPathBase<Obras1> {

    private static final long serialVersionUID = -1946668268L;

    public static final QObras1 obras1 = new QObras1("obras1");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final NumberPath<java.math.BigDecimal> canpol = createNumber("canpol", java.math.BigDecimal.class);

    public final StringPath caopol = createString("caopol");

    public final StringPath capcto = createString("capcto");

    public final StringPath capsal = createString("capsal");

    public final StringPath clvcto = createString("clvcto");

    public final NumberPath<Integer> clvobr = createNumber("clvobr", Integer.class);

    public final NumberPath<Integer> clvprv = createNumber("clvprv", Integer.class);

    public final StringPath concep = createString("concep");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final StringPath ctosal = createString("ctosal");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final DateTimePath<java.util.Date> fecpol = createDateTime("fecpol", java.util.Date.class);

    public final DateTimePath<java.util.Date> fefcto = createDateTime("fefcto", java.util.Date.class);

    public final DateTimePath<java.util.Date> feicto = createDateTime("feicto", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<java.math.BigDecimal> moncto = createNumber("moncto", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> monenc = createNumber("monenc", java.math.BigDecimal.class);

    public final StringPath nomobr = createString("nomobr");

    public final NumberPath<java.math.BigDecimal> refpol = createNumber("refpol", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> renpol = createNumber("renpol", java.math.BigDecimal.class);

    public final StringPath stacto = createString("stacto");

    public final StringPath tippol = createString("tippol");

    public QObras1(String variable) {
        super(Obras1.class, forVariable(variable));
    }

    public QObras1(Path<? extends Obras1> path) {
        super(path.getType(), path.getMetadata());
    }

    public QObras1(PathMetadata<?> metadata) {
        super(Obras1.class, metadata);
    }

}

