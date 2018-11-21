package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPpCapp is a Querydsl query type for PpCapp
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPpCapp extends EntityPathBase<PpCapp> {

    private static final long serialVersionUID = -1906510030L;

    public static final QPpCapp ppCapp = new QPpCapp("ppCapp");

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

    public final StringPath estProy = createString("estProy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> numVer = createNumber("numVer", Integer.class);

    public final StringPath objProy = createString("objProy");

    public final StringPath userid = createString("userid");

    public QPpCapp(String variable) {
        super(PpCapp.class, forVariable(variable));
    }

    public QPpCapp(Path<? extends PpCapp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPpCapp(PathMetadata<?> metadata) {
        super(PpCapp.class, metadata);
    }

}

