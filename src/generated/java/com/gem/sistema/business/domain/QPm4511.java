package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4511 is a Querydsl query type for Pm4511
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4511 extends EntityPathBase<Pm4511> {

    private static final long serialVersionUID = -1909771758L;

    public static final QPm4511 pm4511 = new QPm4511("pm4511");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nhab = createNumber("nhab", Integer.class);

    public final StringPath obsnhab = createString("obsnhab");

    public final StringPath obstotpre = createString("obstotpre");

    public final NumberPath<java.math.BigDecimal> totpre = createNumber("totpre", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm4511(String variable) {
        super(Pm4511.class, forVariable(variable));
    }

    public QPm4511(Path<? extends Pm4511> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4511(PathMetadata<?> metadata) {
        super(Pm4511.class, metadata);
    }

}

