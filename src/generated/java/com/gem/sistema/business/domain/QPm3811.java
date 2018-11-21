package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3811 is a Querydsl query type for Pm3811
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3811 extends EntityPathBase<Pm3811> {

    private static final long serialVersionUID = -1909798666L;

    public static final QPm3811 pm3811 = new QPm3811("pm3811");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath areaafin = createString("areaafin");

    public final StringPath capturo = createString("capturo");

    public final StringPath cert = createString("cert");

    public final StringPath certh = createString("certh");

    public final StringPath doc = createString("doc");

    public final NumberPath<Integer> edad = createNumber("edad", Integer.class);

    public final StringPath espcert = createString("espcert");

    public final StringPath espcerth = createString("espcerth");

    public final StringPath espest = createString("espest");

    public final StringPath estsup = createString("estsup");

    public final StringPath explab = createString("explab");

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

    public final StringPath pubper = createString("pubper");

    public final StringPath pubpuesto = createString("pubpuesto");

    public final StringPath secpub = createString("secpub");

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final StringPath sexo = createString("sexo");

    public final StringPath titingoarq = createString("titingoarq");

    public final StringPath ultlugar = createString("ultlugar");

    public final StringPath ultpuesto = createString("ultpuesto");

    public final StringPath userid = createString("userid");

    public QPm3811(String variable) {
        super(Pm3811.class, forVariable(variable));
    }

    public QPm3811(Path<? extends Pm3811> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3811(PathMetadata<?> metadata) {
        super(Pm3811.class, metadata);
    }

}

