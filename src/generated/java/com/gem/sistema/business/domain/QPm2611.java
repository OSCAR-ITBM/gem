package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2611 is a Querydsl query type for Pm2611
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2611 extends EntityPathBase<Pm2611> {

    private static final long serialVersionUID = -1909830379L;

    public static final QPm2611 pm2611 = new QPm2611("pm2611");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obstot = createString("obstot");

    public final StringPath obstra = createString("obstra");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final StringPath userid = createString("userid");

    public final NumberPath<java.math.BigDecimal> voltot = createNumber("voltot", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> voltra = createNumber("voltra", java.math.BigDecimal.class);

    public QPm2611(String variable) {
        super(Pm2611.class, forVariable(variable));
    }

    public QPm2611(Path<? extends Pm2611> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2611(PathMetadata<?> metadata) {
        super(Pm2611.class, metadata);
    }

}

