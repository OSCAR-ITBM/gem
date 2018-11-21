package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3311 is a Querydsl query type for Pm3311
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3311 extends EntityPathBase<Pm3311> {

    private static final long serialVersionUID = -1909803471L;

    public static final QPm3311 pm3311 = new QPm3311("pm3311");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obspttm = createString("obspttm");

    public final StringPath obsspaem = createString("obsspaem");

    public final NumberPath<Integer> pttm = createNumber("pttm", Integer.class);

    public final NumberPath<Integer> spaem = createNumber("spaem", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm3311(String variable) {
        super(Pm3311.class, forVariable(variable));
    }

    public QPm3311(Path<? extends Pm3311> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3311(PathMetadata<?> metadata) {
        super(Pm3311.class, metadata);
    }

}

