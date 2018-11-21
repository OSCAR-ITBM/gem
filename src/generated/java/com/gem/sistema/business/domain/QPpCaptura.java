package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPpCaptura is a Querydsl query type for PpCaptura
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPpCaptura extends EntityPathBase<PpCaptura> {

    private static final long serialVersionUID = -192546194L;

    public static final QPpCaptura ppCaptura = new QPpCaptura("ppCaptura");

    public final NumberPath<Integer> anioCap = createNumber("anioCap", Integer.class);

    public final StringPath campo0 = createString("campo0");

    public final StringPath campo1 = createString("campo1");

    public final StringPath campo2 = createString("campo2");

    public final StringPath campo3 = createString("campo3");

    public final StringPath campo4 = createString("campo4");

    public final StringPath campo5 = createString("campo5");

    public final NumberPath<Integer> clavedep = createNumber("clavedep", Integer.class);

    public final StringPath clvdep = createString("clvdep");

    public final StringPath clvfuen = createString("clvfuen");

    public final StringPath clvnep = createString("clvnep");

    public final StringPath clvreg = createString("clvreg");

    public final StringPath descProy = createString("descProy");

    public final StringPath dimensionc = createString("dimensionc");

    public final NumberPath<java.math.BigDecimal> dimensiond = createNumber("dimensiond", java.math.BigDecimal.class);

    public final StringPath estProy = createString("estProy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> numVer = createNumber("numVer", Integer.class);

    public final StringPath objProy = createString("objProy");

    public final NumberPath<Integer> sectorid = createNumber("sectorid", Integer.class);

    public final StringPath tipind = createString("tipind");

    public final StringPath userid = createString("userid");

    public QPpCaptura(String variable) {
        super(PpCaptura.class, forVariable(variable));
    }

    public QPpCaptura(Path<? extends PpCaptura> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPpCaptura(PathMetadata<?> metadata) {
        super(PpCaptura.class, metadata);
    }

}

