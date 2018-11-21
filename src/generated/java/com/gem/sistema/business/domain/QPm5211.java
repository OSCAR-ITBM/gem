package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5211 is a Querydsl query type for Pm5211
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5211 extends EntityPathBase<Pm5211> {

    private static final long serialVersionUID = -1909744850L;

    public static final QPm5211 pm5211 = new QPm5211("pm5211");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> acc = createNumber("acc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuacc = createNumber("acuacc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuapmej = createNumber("acuapmej", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuaprov = createNumber("acuaprov", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuder = createNumber("acuder", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuimp = createNumber("acuimp", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuipf = createNumber("acuipf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuipr = createNumber("acuipr", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuprod = createNumber("acuprod", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuting = createNumber("acuting", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> apmej = createNumber("apmej", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aprov = createNumber("aprov", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<java.math.BigDecimal> der = createNumber("der", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> imp = createNumber("imp", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ipf = createNumber("ipf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ipr = createNumber("ipr", java.math.BigDecimal.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final StringPath obsgral = createString("obsgral");

    public final NumberPath<java.math.BigDecimal> prod = createNumber("prod", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ting = createNumber("ting", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm5211(String variable) {
        super(Pm5211.class, forVariable(variable));
    }

    public QPm5211(Path<? extends Pm5211> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5211(PathMetadata<?> metadata) {
        super(Pm5211.class, metadata);
    }

}

