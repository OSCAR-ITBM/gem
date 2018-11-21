package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5011 is a Querydsl query type for Pm5011
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5011 extends EntityPathBase<Pm5011> {

    private static final long serialVersionUID = -1909746772L;

    public static final QPm5011 pm5011 = new QPm5011("pm5011");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> acumtcc = createNumber("acumtcc", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final StringPath obstcc = createString("obstcc");

    public final StringPath obstcr = createString("obstcr");

    public final NumberPath<Integer> tcc = createNumber("tcc", Integer.class);

    public final NumberPath<Integer> tcr = createNumber("tcr", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm5011(String variable) {
        super(Pm5011.class, forVariable(variable));
    }

    public QPm5011(Path<? extends Pm5011> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5011(PathMetadata<?> metadata) {
        super(Pm5011.class, metadata);
    }

}

