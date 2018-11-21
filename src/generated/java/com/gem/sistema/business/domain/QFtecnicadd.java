package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFtecnicadd is a Querydsl query type for Ftecnicadd
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFtecnicadd extends EntityPathBase<Ftecnicadd> {

    private static final long serialVersionUID = -1810633031L;

    public static final QFtecnicadd ftecnicadd = new QFtecnicadd("ftecnicadd");

    public final StringPath clvdep = createString("clvdep");

    public final StringPath clvfin = createString("clvfin");

    public final StringPath clvfun = createString("clvfun");

    public final NumberPath<Integer> codigo = createNumber("codigo", Integer.class);

    public final StringPath cveind = createString("cveind");

    public final StringPath cvetemas = createString("cvetemas");

    public final StringPath cvevar = createString("cvevar");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> metanual = createNumber("metanual", java.math.BigDecimal.class);

    public final StringPath nope = createString("nope");

    public final StringPath pe = createString("pe");

    public final StringPath tema = createString("tema");

    public final StringPath tipoper = createString("tipoper");

    public final NumberPath<java.math.BigDecimal> trim1 = createNumber("trim1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trim2 = createNumber("trim2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trim3 = createNumber("trim3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trim4 = createNumber("trim4", java.math.BigDecimal.class);

    public final StringPath unimed = createString("unimed");

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public final StringPath variables = createString("variables");

    public QFtecnicadd(String variable) {
        super(Ftecnicadd.class, forVariable(variable));
    }

    public QFtecnicadd(Path<? extends Ftecnicadd> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFtecnicadd(PathMetadata<?> metadata) {
        super(Ftecnicadd.class, metadata);
    }

}

