package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3411 is a Querydsl query type for Pm3411
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3411 extends EntityPathBase<Pm3411> {

    private static final long serialVersionUID = -1909802510L;

    public static final QPm3411 pm3411 = new QPm3411("pm3411");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> acuspcam = createNumber("acuspcam", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsspcam = createString("obsspcam");

    public final StringPath obstspaem = createString("obstspaem");

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final NumberPath<Integer> spcam = createNumber("spcam", Integer.class);

    public final NumberPath<Integer> tspaem = createNumber("tspaem", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm3411(String variable) {
        super(Pm3411.class, forVariable(variable));
    }

    public QPm3411(Path<? extends Pm3411> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3411(PathMetadata<?> metadata) {
        super(Pm3411.class, metadata);
    }

}

