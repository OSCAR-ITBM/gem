package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3511 is a Querydsl query type for Pm3511
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3511 extends EntityPathBase<Pm3511> {

    private static final long serialVersionUID = -1909801549L;

    public static final QPm3511 pm3511 = new QPm3511("pm3511");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final StringPath certif = createString("certif");

    public final StringPath docu = createString("docu");

    public final NumberPath<Integer> edad = createNumber("edad", Integer.class);

    public final StringPath especer = createString("especer");

    public final StringPath espest = createString("espest");

    public final StringPath estsup = createString("estsup");

    public final StringPath experi = createString("experi");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final DatePath<java.util.Date> fecing = createDate("fecing", java.util.Date.class);

    public final DatePath<java.util.Date> fipspublico = createDate("fipspublico", java.util.Date.class);

    public final DatePath<java.util.Date> fiupuesto = createDate("fiupuesto", java.util.Date.class);

    public final DatePath<java.util.Date> ftpspublico = createDate("ftpspublico", java.util.Date.class);

    public final DatePath<java.util.Date> ftupuesto = createDate("ftupuesto", java.util.Date.class);

    public final StringPath grado = createString("grado");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final StringPath publugar = createString("publugar");

    public final StringPath pubperiodo = createString("pubperiodo");

    public final StringPath pubpuesto = createString("pubpuesto");

    public final StringPath secpub = createString("secpub");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final StringPath sexo = createString("sexo");

    public final StringPath ultlugar = createString("ultlugar");

    public final StringPath ultperiodo = createString("ultperiodo");

    public final StringPath ultpuesto = createString("ultpuesto");

    public final StringPath userid = createString("userid");

    public QPm3511(String variable) {
        super(Pm3511.class, forVariable(variable));
    }

    public QPm3511(Path<? extends Pm3511> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3511(PathMetadata<?> metadata) {
        super(Pm3511.class, metadata);
    }

}

