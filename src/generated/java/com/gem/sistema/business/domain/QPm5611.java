package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5611 is a Querydsl query type for Pm5611
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5611 extends EntityPathBase<Pm5611> {

    private static final long serialVersionUID = -1909741006L;

    public static final QPm5611 pm5611 = new QPm5611("pm5611");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final DatePath<java.util.Date> fecdep = createDate("fecdep", java.util.Date.class);

    public final DatePath<java.util.Date> fecdepfor = createDate("fecdepfor", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final StringPath mes = createString("mes");

    public final NumberPath<java.math.BigDecimal> mminfism = createNumber("mminfism", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mminfor = createNumber("mminfor", java.math.BigDecimal.class);

    public final NumberPath<Integer> ncbfism = createNumber("ncbfism", Integer.class);

    public final NumberPath<Integer> ncbfor = createNumber("ncbfor", Integer.class);

    public final StringPath obsfism = createString("obsfism");

    public final StringPath obsfor = createString("obsfor");

    public final StringPath userid = createString("userid");

    public QPm5611(String variable) {
        super(Pm5611.class, forVariable(variable));
    }

    public QPm5611(Path<? extends Pm5611> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5611(PathMetadata<?> metadata) {
        super(Pm5611.class, metadata);
    }

}

