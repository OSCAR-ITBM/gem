package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4911 is a Querydsl query type for Pm4911
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4911 extends EntityPathBase<Pm4911> {

    private static final long serialVersionUID = -1909767914L;

    public static final QPm4911 pm4911 = new QPm4911("pm4911");

    public final NumberPath<Integer> acuncpip = createNumber("acuncpip", Integer.class);

    public final NumberPath<Integer> acutcrpip = createNumber("acutcrpip", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final NumberPath<Integer> ncpip = createNumber("ncpip", Integer.class);

    public final StringPath obsncpip = createString("obsncpip");

    public final StringPath obstcrpip = createString("obstcrpip");

    public final NumberPath<Integer> tcrpip = createNumber("tcrpip", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm4911(String variable) {
        super(Pm4911.class, forVariable(variable));
    }

    public QPm4911(Path<? extends Pm4911> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4911(PathMetadata<?> metadata) {
        super(Pm4911.class, metadata);
    }

}

