package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0811 is a Querydsl query type for Pm0811
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0811 extends EntityPathBase<Pm0811> {

    private static final long serialVersionUID = -1909888039L;

    public static final QPm0811 pm0811 = new QPm0811("pm0811");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nspc = createNumber("nspc", Integer.class);

    public final StringPath obsnspc = createString("obsnspc");

    public final StringPath obstesp = createString("obstesp");

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final NumberPath<Integer> tesp = createNumber("tesp", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0811(String variable) {
        super(Pm0811.class, forVariable(variable));
    }

    public QPm0811(Path<? extends Pm0811> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0811(PathMetadata<?> metadata) {
        super(Pm0811.class, metadata);
    }

}

