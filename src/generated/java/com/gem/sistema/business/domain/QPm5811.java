package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5811 is a Querydsl query type for Pm5811
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5811 extends EntityPathBase<Pm5811> {

    private static final long serialVersionUID = -1909739084L;

    public static final QPm5811 pm5811 = new QPm5811("pm5811");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath docu = createString("docu");

    public final StringPath espest = createString("espest");

    public final StringPath estsup = createString("estsup");

    public final StringPath experi = createString("experi");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final DatePath<java.util.Date> fecing = createDate("fecing", java.util.Date.class);

    public final StringPath grado = createString("grado");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final DatePath<java.util.Date> pubfecfin = createDate("pubfecfin", java.util.Date.class);

    public final DatePath<java.util.Date> pubfecini = createDate("pubfecini", java.util.Date.class);

    public final StringPath publugar = createString("publugar");

    public final StringPath pubpuesto = createString("pubpuesto");

    public final StringPath secpub = createString("secpub");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final DatePath<java.util.Date> ultfecfin = createDate("ultfecfin", java.util.Date.class);

    public final DatePath<java.util.Date> ultfecini = createDate("ultfecini", java.util.Date.class);

    public final StringPath ultlugar = createString("ultlugar");

    public final StringPath ultpuesto = createString("ultpuesto");

    public final StringPath userid = createString("userid");

    public QPm5811(String variable) {
        super(Pm5811.class, forVariable(variable));
    }

    public QPm5811(Path<? extends Pm5811> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5811(PathMetadata<?> metadata) {
        super(Pm5811.class, metadata);
    }

}

