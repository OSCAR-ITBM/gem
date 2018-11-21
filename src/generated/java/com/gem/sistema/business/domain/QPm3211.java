package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3211 is a Querydsl query type for Pm3211
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3211 extends EntityPathBase<Pm3211> {

    private static final long serialVersionUID = -1909804432L;

    public static final QPm3211 pm3211 = new QPm3211("pm3211");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nobs = createNumber("nobs", Integer.class);

    public final StringPath obsnobs = createString("obsnobs");

    public final StringPath obstotobr = createString("obstotobr");

    public final NumberPath<Integer> totobr = createNumber("totobr", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm3211(String variable) {
        super(Pm3211.class, forVariable(variable));
    }

    public QPm3211(Path<? extends Pm3211> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3211(PathMetadata<?> metadata) {
        super(Pm3211.class, metadata);
    }

}

